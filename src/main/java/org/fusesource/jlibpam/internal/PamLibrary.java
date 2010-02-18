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
package org.fusesource.jlibpam.internal;

import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.Library;

import static org.fusesource.hawtjni.runtime.ArgFlag.CRITICAL;
import static org.fusesource.hawtjni.runtime.ArgFlag.NO_IN;
import static org.fusesource.hawtjni.runtime.ArgFlag.NO_OUT;
import static org.fusesource.hawtjni.runtime.ClassFlag.STRUCT;
import static org.fusesource.hawtjni.runtime.FieldFlag.CONSTANT;
import static org.fusesource.hawtjni.runtime.MethodFlag.CONSTANT_INITIALIZER;

/**
 * PAM Library
 *
 * @author <a href="mailto:gnodet[at]gmail.com">Guillaume Nodet</a>
 */
@JniClass(conditional="defined(HAVE_SECURITY_PAM_APPL_H)")
public class PamLibrary {
	
	public static final Library LIBRARY = new Library("jlibpam", PamLibrary.class);
	static {
		LIBRARY.load();
		init();
	}

    @JniMethod(flags={CONSTANT_INITIALIZER})
    public static final native void init();

    @JniField(flags=CONSTANT)
    public static int PAM_SUCCESS;

    @JniField(flags=CONSTANT)
    public static int PAM_OPEN_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_SYMBOL_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_SERVICE_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_SYSTEM_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_BUF_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_CONV_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_PERM_DENIED;

    @JniField(flags=CONSTANT)
    public static int PAM_MAXTRIES;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTH_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_NEW_AUTHTOK_REQD;

    @JniField(flags=CONSTANT)
    public static int PAM_CRED_INSUFFICIENT;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTHINFO_UNAVAIL;

    @JniField(flags=CONSTANT)
    public static int PAM_USER_UNKNOWN;

    @JniField(flags=CONSTANT)
    public static int PAM_CRED_UNAVAIL;

    @JniField(flags=CONSTANT)
    public static int PAM_CRED_EXPIRED;

    @JniField(flags=CONSTANT)
    public static int PAM_CRED_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_ACCT_EXPIRED;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTHTOK_EXPIRED;

    @JniField(flags=CONSTANT)
    public static int PAM_SESSION_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTHTOK_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTHTOK_RECOVERY_ERR;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTHTOK_LOCK_BUSY;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTHTOK_DISABLE_AGING;

    @JniField(flags=CONSTANT)
    public static int PAM_NO_MODULE_DATA;

    @JniField(flags=CONSTANT)
    public static int PAM_IGNORE;

    @JniField(flags=CONSTANT)
    public static int PAM_ABORT;

    @JniField(flags=CONSTANT)
    public static int PAM_TRY_AGAIN;

    @JniField(flags=CONSTANT)
    public static int PAM_MODULE_UNKNOWN;

    @JniField(flags=CONSTANT)
    public static int PAM_DOMAIN_UNKNOWN;

    @JniField(flags=CONSTANT)
    public static int PAM_PROMPT_ECHO_OFF;

    @JniField(flags=CONSTANT)
    public static int PAM_PROMPT_ECHO_ON;

    @JniField(flags=CONSTANT)
    public static int PAM_ERROR_MSG;

    @JniField(flags=CONSTANT)
    public static int PAM_TEXT_INFO;

    @JniField(flags=CONSTANT)
    public static int PAM_MAX_NUM_MSG;

    @JniField(flags=CONSTANT)
    public static int PAM_MAX_MSG_SIZE;

    @JniField(flags=CONSTANT)
    public static int PAM_MAX_RESP_SIZE;

    @JniField(flags=CONSTANT)
    public static int PAM_SILENT;

    @JniField(flags=CONSTANT)
    public static int PAM_DISALLOW_NULL_AUTHTOK;

    @JniField(flags=CONSTANT)
    public static int PAM_ESTABLISH_CRED;

    @JniField(flags=CONSTANT)
    public static int PAM_DELETE_CRED;

    @JniField(flags=CONSTANT)
    public static int PAM_REINITIALIZE_CRED;

    @JniField(flags=CONSTANT)
    public static int PAM_REFRESH_CRED;

    @JniField(flags=CONSTANT)
    public static int PAM_PRELIM_CHECK;

    @JniField(flags=CONSTANT)
    public static int PAM_UPDATE_AUTHTOK;

    @JniField(flags=CONSTANT)
    public static int PAM_CHANGE_EXPIRED_AUTHTOK;

    @JniField(flags=CONSTANT)
    public static int PAM_SERVICE;

    @JniField(flags=CONSTANT)
    public static int PAM_USER;

    @JniField(flags=CONSTANT)
    public static int PAM_TTY;

    @JniField(flags=CONSTANT)
    public static int PAM_RHOST;

    @JniField(flags=CONSTANT)
    public static int PAM_CONV;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTHTOK;

    @JniField(flags=CONSTANT)
    public static int PAM_OLDAUTHTOK;

    @JniField(flags=CONSTANT)
    public static int PAM_RUSER;

    @JniField(flags=CONSTANT)
    public static int PAM_USER_PROMPT;

    @JniField(flags=CONSTANT)
    public static int PAM_REPOSITORY;

    @JniField(flags=CONSTANT)
    public static int PAM_AUTHTOK_PROMPT;

    @JniField(flags=CONSTANT)
    public static int PAM_OLDAUTHTOK_PROMPT;


    /**
     * <code><pre>
     * int pam_acct_mgmt(pam_handle_t *pamh, int flags)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_acct_mgmt(
		@JniArg(cast="pam_handle_t *") long pamh,
		@JniArg int flags);

    /**
     * <code><pre>
     * int pam_authenticate(pam_handle_t *pamh, int flags)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_authenticate(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg int flags);

    /**
     * <code><pre>
     * int pam_chauthtok(pam_handle_t *pamh, int flags)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_chauthtok(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg int flags);

    /**
     * <code><pre>
     * int pam_close_session(pam_handle_t *pamh, int flags)
     */
    @JniMethod
	public static final native int pam_close_session(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg int flags);

    /**
     * <code><pre>
     * int pam_end(pam_handle_t *pamh, int status)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_end(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg int status);

    /**
     * <code><pre>
     * int pam_get_data(const pam_handle_t *pamh, const char *module_data_name, const void **data)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_get_data(
			@JniArg(cast="const pam_handle_t *") long pamh,
            @JniArg(cast="const char *", flags=CRITICAL) String module_data_name,
		 	@JniArg(cast="void **") long data);

    /**
     * <code><pre>
     * int pam_get_item(const pam_handle_t *pamh, int item_type, const void **item)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_get_item(
			@JniArg(cast="const pam_handle_t *") long pamh,
			@JniArg int item_type,
			@JniArg(cast="void **") long[] item);

    /**
     * <code><pre>
     * int pam_get_user(pam_handle_t *pamh, const char **user, const char *prompt)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_get_user(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg(cast="const char **", flags={NO_IN, CRITICAL}) long[] user,
			@JniArg(cast="const char *", flags=CRITICAL) String prompt);

    /**
     * <code><pre>
     * const char * pam_getenv(pam_handle_t *pamh, const char *name)
     * </pre></code>
     */
	@JniMethod
    public static final native long pam_getenv(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg(cast="const char *", flags=CRITICAL) String name);
			
    /**
     * <code><pre>
     * char ** pam_getenvlist(pam_handle_t * pamh);
     * </pre></code>
     */
    @JniMethod(conditional = "defined(HAVE_PAM_GETENVLIST)")
    public static final native long pam_getenvlist(
            @JniArg(cast="pam_handle_t *") long pamh);

    /**
     * <code><pre>
     * int pam_open_session(pam_handle_t *pamh, int flags)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_open_session(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg int flags);

    /**
     * <code><pre>
     * int pam_putenv(pam_handle_t *pamh, const char *namevalue)
     * </pre></code>
     */
    @JniMethod(conditional = "defined(HAVE_PAM_PUTENV)")
	public static final native int pam_putenv(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg(cast="const char *", flags=CRITICAL) String namevalue);

    /**
     * <code><pre>
     * int pam_set_data(pam_handle_t *pamh, const char *module_data_name, void *data,
     *                  void (*cleanup)(pam_handle_t *pamh, void *data, int pam_end_status))
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_set_data(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg(cast="const char *", flags=CRITICAL) String module_data_name,
		 	@JniArg(cast="void *") long data,
		 	@JniArg(cast="void *") long cleanup);

    /**
     * <code><pre>
     * int pam_set_item(pam_handle_t *pamh, int item_type, const void *item)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_set_item(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg int item_type, 
			@JniArg(cast="const void *", flags=CRITICAL) String item);

    /**
     * <code><pre>
     * int pam_set_item(pam_handle_t *pamh, int item_type, const void *item)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_set_item(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg int item_type,
			@JniArg(cast="const void *", flags=CRITICAL) pam_conv item);

    /**
     * <code><pre>
     * int pam_setcred(pam_handle_t *pamh, int flags)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_setcred(
			@JniArg(cast="pam_handle_t *") long pamh,
			int flags);

    /**
     * <code><pre>
     * int pam_start(const char *service, const char *user,
     *               const struct pam_conv *pam_conv, pam_handle_t **pamh)
     * </pre></code>
     */
    @JniMethod
	public static final native int pam_start(
			@JniArg(cast="const char *", flags=CRITICAL) String service, 
			@JniArg(cast="const char *", flags=CRITICAL) String user, 
		 	@JniArg(flags = NO_OUT) pam_conv pam_conv, 
			@JniArg(cast="pam_handle_t **", flags={NO_IN, CRITICAL}) long[] pamh);

    /**
     * <code><pre>
     * const char * pam_strerror(const pam_handle_t *pamh, int error_number);
     * </pre></code>
     */
	@JniMethod
	public static final native long pam_strerror(
			@JniArg(cast="pam_handle_t *") long pamh,
			@JniArg int error_number
			);

    /**
     * <code><pre>
     * struct pam_conv {
	 *     int (*conv)(int, const struct pam_message **, struct pam_response **, void *);
	 *     void *appdata_ptr;
     * }
     * </pre></code>
     */
    @JniClass(flags=STRUCT, conditional="defined(HAVE_SECURITY_PAM_APPL_H)")
    public static class pam_conv { 
        static {
            LIBRARY.load();
            init();
        }
        
        @JniMethod(flags={CONSTANT_INITIALIZER})
        public static final native void init();

        @JniField(flags={CONSTANT}, accessor="sizeof(struct pam_conv)")
        public static int SIZEOF;

        @JniField
        public long conv;

        @JniField
        public long appdata_ptr;

	}

    /**
     * <code><pre>
     * struct pam_message {
	 *     int msg_style;
	 *     char *msg;
     * }
     * </pre></code>
     */
    @JniClass(flags=STRUCT, conditional="defined(HAVE_SECURITY_PAM_APPL_H)")
    public static class pam_message {
        static {
            LIBRARY.load();
            init();
        }

        @JniMethod(flags={CONSTANT_INITIALIZER})
        public static final native void init();

        @JniField(flags={CONSTANT}, accessor="sizeof(struct pam_message)")
        public static int SIZEOF;

        @JniField
        public int msg_style;

        @JniField(cast = "char *")
        public long msg;

        @JniMethod
        public static final native void memmove (
                @JniArg(cast="void *", flags={NO_IN, CRITICAL}) pam_message dest,
                @JniArg(cast="const void *") long src,
                @JniArg(cast="size_t") long size);

        @JniMethod
        public static final native void memmove (
                @JniArg(cast="void *") long dest,
                @JniArg(cast="const void *", flags={NO_OUT, CRITICAL}) pam_message src, 
                @JniArg(cast="size_t") long size);
    }

    /**
     * <code><pre>
     * struct pam_response {
	 *     char *resp;
     *     int resp_retcode;
     * }
     * </pre></code>
     */
    @JniClass(flags=STRUCT, conditional="defined(HAVE_SECURITY_PAM_APPL_H)")
    public static class pam_response {
        static {
            LIBRARY.load();
            init();
        }

        @JniMethod(flags={CONSTANT_INITIALIZER})
        public static final native void init();

        @JniField(flags={CONSTANT}, accessor="sizeof(struct pam_response)")
        public static int SIZEOF;

        @JniField(cast = "char *")
        public long resp;

        @JniField
        public int resp_retcode;

        @JniMethod
        public static final native void memmove (
                @JniArg(cast="void *", flags={NO_IN, CRITICAL}) pam_response dest,
                @JniArg(cast="const void *") long src,
                @JniArg(cast="size_t") long size);

        @JniMethod
        public static final native void memmove (
                @JniArg(cast="void *") long dest,
                @JniArg(cast="const void *", flags={NO_OUT, CRITICAL}) pam_response src,
                @JniArg(cast="size_t") long size);

    }

}