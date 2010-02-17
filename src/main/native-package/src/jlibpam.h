/*******************************************************************************
 * Copyright (C) 2009, Progress Software Corporation and/or its 
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
#ifndef JLIBPAM_H
#define JLIBPAM_H

#ifdef HAVE_CONFIG_H
  /* configure based build.. we will use what it discovered about the platform */
  #include "config.h"
#else
  #ifdef WIN32
    /* Windows based build */
    #define HAVE_STDLIB_H 1
    #define HAVE_STRINGS_H 1
  #endif
#endif

#ifdef HAVE_UNISTD_H
  #include <unistd.h>
#endif

#ifdef HAVE_STDLIB_H
  #include <stdlib.h>
#endif

#ifdef HAVE_STRINGS_H
  #include <string.h>
#endif

#ifdef HAVE_SECURITY_PAM_APPL_H
  #include <system/pam_appl.h>
#endif

#endif /* JLIBPAM_H */
