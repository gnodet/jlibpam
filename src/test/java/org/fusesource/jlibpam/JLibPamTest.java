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

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JLibPamTest {

	@Test
	public void testSetGetItem() {
        PamContext pam = new PamContext("service", "user");
        pam.setItem(PamContext.Item.AuthToken, "authtok");
        assertEquals("service", pam.getItem(PamContext.Item.Service));
        assertEquals("user", pam.getItem(PamContext.Item.User));
        assertEquals("authtok", pam.getItem(PamContext.Item.AuthToken));
        pam.close();
	}

    @Test(expected = PamException.class)
    public void testGetUserWithNoUserAndNoConv() {
        PamContext pam = new PamContext("sshd", null);
        pam.getUser();
        pam.close();
    }

    @Test
    public void testGetUserWithUser() {
        PamContext pam = new PamContext("sshd", "user");
        assertEquals("user", pam.getUser());
        pam.close();
    }

    @Test
    public void testGetUserWithConv() {
        PamContext pam = new PamContext("sshd", null, new PamContext.Conv() {
            public void callback(PamContext.Message[] messages, PamContext.Response[] responses) throws PamException {
                assertNotNull(messages);
                assertNotNull(responses);
                assertEquals(1, messages.length);
                assertEquals(1, responses.length);
                assertNotNull(messages[0]);
                assertNull(responses[0]);
                responses[0] = new PamContext.Response("user");
            }
        });
        assertEquals("user", pam.getUser());
        pam.close();
    }

    @Test
    public void testGetUserWithConvAndPrompt() {
        PamContext pam = new PamContext("sshd", null, new PamContext.Conv() {
            public void callback(PamContext.Message[] messages, PamContext.Response[] responses) throws PamException {
                assertNotNull(messages);
                assertNotNull(responses);
                assertEquals(1, messages.length);
                assertEquals(1, responses.length);
                assertNotNull(messages[0]);
                assertEquals("prompt", messages[0].msg);
                assertNull(responses[0]);
                responses[0] = new PamContext.Response("user");
            }
        });
        assertEquals("user", pam.getUser("prompt"));
        pam.close();
    }

}