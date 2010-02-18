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

/**
 * PAM context for high level access
 *
 * @author <a href="mailto:gnodet[at]gmail.com">Guillaume Nodet</a>
 */
public final class Pam {

    private Pam() {
    }

    /**
     * Simple authentication method which will authenticate the user
     * or throw an exception
     *
     * @param system the PAM system
     * @param user the user name
     * @param password the user password
     * @throws PamException if an error occurs or the authentication fails
     */
    public void authenticate(String system, String user, String password) throws PamException {
        PamContext pam = new PamContext(system, user);
        try {
            pam.setItem(PamContext.Item.AuthToken, password);
            pam.authenticate();
            // success
        } catch (PamException e) {
            // failure
            throw e;
        } finally {
            pam.close();
        }
    }

}
