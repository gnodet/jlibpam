dnl ---------------------------------------------------------------------------
dnl  Copyright (C) 2009 Progress Software, Inc.
dnl  http://fusesource.com
dnl  
dnl  Licensed under the Apache License, Version 2.0 (the "License");
dnl  you may not use this file except in compliance with the License.
dnl  You may obtain a copy of the License at
dnl  
dnl     http://www.apache.org/licenses/LICENSE-2.0
dnl  
dnl  Unless required by applicable law or agreed to in writing, software
dnl  distributed under the License is distributed on an "AS IS" BASIS,
dnl  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
dnl  See the License for the specific language governing permissions and
dnl  limitations under the License.
dnl ---------------------------------------------------------------------------

AC_DEFUN([CUSTOM_M4_SETUP],
[
    AC_CHECK_HEADER([security/pam_appl.h],[
        AC_CHECK_LIB([pam], [pam_start],[
            LDFLAGS="$LDFLAGS -lpam"
            AC_DEFINE([HAVE_SECURITY_PAM_APPL_H], [1], [Define to 1 if you have the <security/pam_appl.h> header file.])
            AC_CHECK_FUNCS(pam_getenvlist)
            AC_CHECK_FUNCS(pam_putenv)
            break
        ])
    ])

    AC_CHECK_HEADER([sys/errno.h],[AC_DEFINE([HAVE_SYS_ERRNO_H], [1], [Define to 1 if you have the <sys/errno.h> header file.])])

])

