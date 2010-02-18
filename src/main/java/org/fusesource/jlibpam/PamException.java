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

import java.util.HashMap;
import java.util.Map;

import static org.fusesource.jlibpam.internal.PamLibrary.*;

/**
 * Exception thrown by the PAM context for any PAM related error.
 *
 * @author <a href="mailto:gnodet[at]gmail.com">Guillaume Nodet</a>
 */
public class PamException extends SecurityException {

    private final Code code;

    public PamException(Code code, String message) {
        super(message);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (msg != null) {
            return msg + " (" + code + ": " + code.toInt() + ")";
        } else {
            return code + ": " + code.toInt();
        }
    }

    public enum Code {

        Success(PAM_SUCCESS),
        OpenErr(PAM_OPEN_ERR),
        SymbolErr(PAM_SYMBOL_ERR),
        ServiceErr(PAM_SERVICE_ERR),
        SystemErr(PAM_SYSTEM_ERR),
        BufErr(PAM_BUF_ERR),
        ConvErr(PAM_CONV_ERR),
        PermDenied(PAM_PERM_DENIED),
        MaxTries(PAM_MAXTRIES),
        AuthenticationError(PAM_AUTH_ERR),
        NewAuthTokenRequired(PAM_NEW_AUTHTOK_REQD),
        CredInsufficient(PAM_CRED_INSUFFICIENT),
        AuthInfoUnavail(PAM_AUTHINFO_UNAVAIL),
        UserUnknown(PAM_USER_UNKNOWN),
        CredUnavail(PAM_CRED_UNAVAIL),
        CredExpired(PAM_CRED_EXPIRED),
        CredErr(PAM_CRED_ERR),
        AcctExpired(PAM_ACCT_EXPIRED),
        AuthTokExpired(PAM_AUTHTOK_EXPIRED),
        SessionErr(PAM_SESSION_ERR),
        AuthTokErr(PAM_AUTHTOK_ERR),
        AuthTokRecoveryErr(PAM_AUTHTOK_RECOVERY_ERR),
        AuthTokLockBusy(PAM_AUTHTOK_LOCK_BUSY),
        AuthTokDisableAging(PAM_AUTHTOK_DISABLE_AGING),
        NoModuleData(PAM_NO_MODULE_DATA),
        Ignore(PAM_IGNORE),
        Abort(PAM_ABORT),
        TryAgain(PAM_TRY_AGAIN),
        ModuleUnknown(PAM_MODULE_UNKNOWN),
        DomainUnkown(PAM_DOMAIN_UNKNOWN);

        private final int val;

        private Code(int val) {
            this.val = val;
        }

        public int toInt() {
            return val;
        }

        static Map<Integer, Code> codes;

        static {
            codes = new HashMap<Integer, Code>();
            for (Code c : Code.values()) {
                codes.put(c.toInt(), c);
            }
        }

        public static Code fromInt(int b) {
            return codes.get(b);
        }
    }
}
