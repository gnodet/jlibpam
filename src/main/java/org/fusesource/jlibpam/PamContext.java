/*******************************************************************************
 * Copyright (C) 2010, Progress Software Corporation and/or its
 * subsidiaries or affiliates.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.fusesource.jlibpam;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.fusesource.hawtjni.runtime.Callback;
import org.fusesource.jlibpam.internal.PamLibrary;
import static org.fusesource.jlibpam.internal.CLibrary.*;
import static org.fusesource.jlibpam.internal.PamLibrary.*;

/**
 * PAM context for low level access
 *
 * @author <a href="mailto:gnodet[at]gmail.com">Guillaume Nodet</a>
 */
public class PamContext {

    static {
        PamLibrary.LIBRARY.load();
    }

    private long handle;
    private int retval;
    private Conv conv;

    /**
     * Creates a new PAM context for the given service and user
     *
     * @param service the PAM service
     * @param user the user
     * @throws PamException if an error occurs
     */
    public PamContext(String service, String user) throws PamException {
        this(service, user, null);
    }

    /**
     * Creates a new PAM context for the given service and user
     *
     * @param service the PAM service
     * @param user the user
     * @throws PamException if an error occurs
     */
    public PamContext(String service, String user, Conv conv) throws PamException {
        this.conv = conv;
        ConvSupport cs = conv != null ? new ConvSupport(conv) : null;
        long[] pamh = new long[1];
        this.retval = pam_start(service, user, cs != null ? cs.pam_conv : null, pamh);
        this.handle = pamh[0];
        if (cs != null) {
            cs.close();
        }
        if (this.retval != PAM_SUCCESS) {
            close();
            throw createException();
        }
    }

    /**
     * Set the specified item value.
     *
     * @param item
     * @param value
     * @throws PamException if an error occurs
     */
    public void setItem(Item item, String value) throws PamException {
        checkResult(pam_set_item(handle, item.toInt(), value));
    }

    /**
     * Retrieve the specified item value.
     *
     * @param item
     * @return
     * @throws PamException if an error occurs
     */
    public String getItem(Item item) throws PamException {
        long[] ret = new long[1];
        checkResult(pam_get_item(handle, item.toInt(), ret));
        return string(ret[0]);
    }

    /**
     * Retrieve the configured callback for PAM conversation
     */
    public Conv getConv() {
        return conv;
    }

    /**
     * Set the specified callback for PAM conversation
     *
     * @param conv the new callback to use
     */
    public void setConv(Conv conv) {
        this.conv = conv;
    }

    /**
     * Authenticate
     *
     * @throws PamException if an error occurs
     */
    public void authenticate() throws PamException {
        authenticate(EnumSet.noneOf(AuthenticationFlag.class));
    }

    /**
     * Authenticate
     *
     * @param flags authentication flags
     * @throws PamException if an error occurs
     */
    public void authenticate(EnumSet<AuthenticationFlag> flags) throws PamException {
        ConvSupport cs = prepareConv();
        try {
            checkResult(pam_authenticate(handle, AuthenticationFlag.value(flags)));
        } finally {
            cs.close();
        }
    }

    /**
     * Open a new session for a previously successful authenticated user.
     * The session should later be terminated with a call to {@link #closeSession()}.
     *
     * @throws PamException if an error occurs
     */
    public void openSession() throws PamException {
        openSession(EnumSet.noneOf(SessionFlag.class));
    }

    /**
     * Open a new session for a previously successful authenticated user.
     * The session should later be terminated with a call to {@link #closeSession()}.
     *
     * @param flags
     * @throws PamException if an error occurs
     */
    public void openSession(EnumSet<SessionFlag> flags) throws PamException {
        ConvSupport cs = prepareConv();
        try {
            checkResult(pam_open_session(handle, SessionFlag.value(flags)));
        } finally {
            cs.close();
        }
    }

    /**
     * Closes a previously opened session.
     *
     * @throws PamException if an error occurs
     */
    public void closeSession() throws PamException {
        closeSession(EnumSet.noneOf(SessionFlag.class));
    }

    public void closeSession(EnumSet<SessionFlag> flags) throws PamException {
        ConvSupport cs = prepareConv();
        try {
            checkResult(pam_close_session(handle, SessionFlag.value(flags)));
        } finally {
            cs.close();
        }
    }

    /**
     * Manage user credentials.
     *
     * @param flag
     * @throws PamException if an error occurs
     */
    public void setCredential(CredFlag flag) throws PamException {
        ConvSupport cs = prepareConv();
        try {
            checkResult(pam_setcred(handle, flag.toInt()));
        } finally {
            cs.close();
        }
    }

    /**
     * Retrieve or ask for the user name
     *
     * @return
     * @throws PamException if an error occurs
     */
    public String getUser() throws PamException {
        return getUser(null);
    }

    /**
     * Retrieve or ask for the user name
     *
     * @param prompt
     * @return
     * @throws PamException if an error occurs
     */
    public String getUser(String prompt) throws PamException {
        ConvSupport cs = prepareConv();
        try {
            long[] puser = new long[1];
            checkResult(pam_get_user(handle, puser, prompt));
            return string(puser[0]);
        } finally {
            cs.close();
        }
    }

    /**
     * Close and destroy this PAM context.
     * Must always be called.
     */
    public void close() {
        try {
            retval = PamLibrary.pam_end(handle, retval);
        } finally {
            handle = 0;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
    }

    protected void checkResult(int retval) {
        this.retval = retval;
        if (this.retval != PAM_SUCCESS) {
            throw createException();
        }
    }

    protected PamException createException() {
        return new PamException(PamException.Code.fromInt(retval), string(pam_strerror(handle, retval)));
    }

    protected ConvSupport prepareConv() {
        ConvSupport cv = new ConvSupport(this.conv);
        checkResult(pam_set_item(handle, PAM_CONV, cv.pam_conv));
        return cv;
    }

    protected static class ConvSupport {
        Callback callback;
        pam_conv pam_conv;
        Conv conv;

        public ConvSupport(Conv conv) {
            this.conv = conv;
            if (conv != null) {
                this.callback = new Callback(this, "doCallback", 4, true);
            }
            this.pam_conv = new pam_conv();
            this.pam_conv.conv = conv != null ? this.callback.getAddress() : NULL;
        }

        public void close() {
            if (callback != null) {
                callback.dispose();
            }
        }

        private long doCallback(long[] args) {
            // convert forth
            int nbMessage = (int) args[0];
            long[] msgPtr = new long[nbMessage];
            memmove(msgPtr, args[1], nbMessage * SIZEOF_LONG);
            Message[] msgs = new Message[nbMessage];
            Response[] resps = new Response[nbMessage];
            for (int i = 0; i < nbMessage; i++) {
                pam_message m = new PamLibrary.pam_message();
                pam_message.memmove(m, msgPtr[i], pam_message.SIZEOF);
                msgs[i] = new Message(Style.fromInt(m.msg_style), string(m.msg));
            }
            try {
                // call
                conv.callback(msgs, resps);
                // convert back
                long presps = malloc(resps.length * pam_response.SIZEOF);
                long p = presps;
                for (int i = 0; i < resps.length; i++, p += pam_response.SIZEOF) {
                    pam_response r = new pam_response();
                    r.resp = strdup(resps[i].msg);
                    r.resp_retcode = resps[i].code.toInt();
                    pam_response.memmove(p, r, pam_response.SIZEOF);
                }
                memmove(args[2], new long[] { presps }, SIZEOF_LONG);
                return PAM_SUCCESS;
            } catch (PamException e) {
                // TODO: log excpetion
                return e.getCode().toInt();
            } catch (Throwable e) {
                // TODO: log excpetion
                return PAM_CONV_ERR;
            }
        }

    }

    public enum CredFlag {

        Establish(PAM_ESTABLISH_CRED),
        Delete(PAM_DELETE_CRED),
        Reinitialize(PAM_REINITIALIZE_CRED),
        Refresh(PAM_REFRESH_CRED);

        private final int val;
        private CredFlag(int val) {
            this.val = val;
        }

        public int toInt() {
            return val;
        }
    }

    public enum AuthenticationFlag {

        Silent(PAM_SILENT),
        DisallowNullAuthToken(PAM_DISALLOW_NULL_AUTHTOK);

        public static int value(EnumSet<AuthenticationFlag> flags) {
            int value = 0;
            for (AuthenticationFlag f : flags) {
                value += f.val;
            }
            return value;
        }

        private final int val;
        private AuthenticationFlag(int val) {
            this.val = val;
        }

        public int toInt() {
            return val;
        }
    }

    public enum SessionFlag {

        Silent(PAM_SILENT);

        public static int value(EnumSet<SessionFlag> flags) {
            int value = 0;
            for (SessionFlag f : flags) {
                value += f.val;
            }
            return value;
        }

        private final int val;
        private SessionFlag(int val) {
            this.val = val;
        }

        public int toInt() {
            return val;
        }
    }

    public enum Item {
        Service(PAM_SERVICE),
        User(PAM_USER),
        Tty(PAM_TTY),
        RHost(PAM_RHOST),
        AuthToken(PAM_AUTHTOK),
        OldAuthToken(PAM_OLDAUTHTOK),
        RUser(PAM_RUSER),
        UserPrompt(PAM_USER_PROMPT),
        Repository(PAM_REPOSITORY);

        private final int val;
        private Item(int val) {
            this.val = val;
        }

        public int toInt() {
            return val;
        }
    }

    public interface Conv {

        void callback(Message[] messages, Response[] responses) throws PamException;

    }

    public enum Style {

        PromptEchoOff(PAM_PROMPT_ECHO_OFF),
        PromptEchoOn(PAM_PROMPT_ECHO_ON),
        ErrorMsg(PAM_ERROR_MSG),
        TextInfo(PAM_TEXT_INFO);

        private final int val;

        private Style(int val) {
            this.val = val;
        }

        public int toInt() {
            return val;
        }

        static Map<Integer, Style> styles;

        static {
            styles = new HashMap<Integer, Style>();
            for (Style c : Style.values()) {
                styles.put(c.toInt(), c);
            }
        }

        public static Style fromInt(int b) {
            return styles.get(b);
        }

    }


    public static class Message {
        public final Style style;
        public final String msg;

        public Message(Style style, String msg) {
            this.style = style;
            this.msg = msg;
        }
    }

    public static class Response {
        public final String msg;
        public final PamException.Code code;

        public Response(String msg) {
            this(msg, PamException.Code.Success);
        }
        public Response(String msg, PamException.Code code) {
            this.msg = msg;
            this.code = code;
        }
    }
}
