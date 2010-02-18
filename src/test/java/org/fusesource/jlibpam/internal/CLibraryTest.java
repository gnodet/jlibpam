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

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.fusesource.jlibpam.internal.CLibrary.*;

public class CLibraryTest {

    @Test
    public void testLong() {
        long[] p1 = new long[] { 1, 2, 3, 4 };
        long[] p2 = new long[p1.length];

        int length = p1.length * 8;
        long ptr = malloc(length + 16);
        byte[] over = getOverflow(length + 16);
        memmove(ptr, over, length + 16);

        memmove(ptr + 8, p1, length);
        memmove(p2, ptr + 8, length);

        memmove(over, ptr, length + 16);

        for (int i = 0; i < 8; i++) {
            assertEquals(over[i], (byte) 0xFF);
            assertEquals(over[length + 8 + i], (byte) 0xFF);
        }
        for (int i = 0; i < p1.length; i++) {
            assertEquals(p1[i], p2[i]);
        }
    }

    @Test
    public void testInt() {
        int[] p1 = new int[] { 1, 2, 3, 4 };
        int[] p2 = new int[p1.length];

        int length = p1.length * 4;
        long ptr = malloc(length + 16);
        byte[] over = getOverflow(length + 16);
        memmove(ptr, over, length + 16);

        memmove(ptr + 8, p1, length);
        memmove(p2, ptr + 8, length);

        memmove(over, ptr, length + 16);

        for (int i = 0; i < 8; i++) {
            assertEquals(over[i], (byte) 0xFF);
            assertEquals(over[length + 8 + i], (byte) 0xFF);
        }
        for (int i = 0; i < p1.length; i++) {
            assertEquals(p1[i], p2[i]);
        }
    }

    @Test
    public void testShort() {
        short[] p1 = new short[] { 1, 2, 3, 4 };
        short[] p2 = new short[p1.length];

        int length = p1.length * 2;
        long ptr = malloc(length + 16);
        byte[] over = getOverflow(length + 16);
        memmove(ptr, over, length + 16);

        memmove(ptr + 8, p1, length);
        memmove(p2, ptr + 8, length);

        memmove(over, ptr, length + 16);

        for (int i = 0; i < 8; i++) {
            assertEquals(over[i], (byte) 0xFF);
            assertEquals(over[length + 8 + i], (byte) 0xFF);
        }
        for (int i = 0; i < p1.length; i++) {
            assertEquals(p1[i], p2[i]);
        }
    }

    @Test
    public void testByte() {
        byte[] p1 = new byte[] { 1, 2, 3, 4 };
        byte[] p2 = new byte[p1.length];

        int length = p1.length * 1;
        long ptr = malloc(length + 16);
        byte[] over = getOverflow(length + 16);
        memmove(ptr, over, length + 16);

        memmove(ptr + 8, p1, length);
        memmove(p2, ptr + 8, length);

        memmove(over, ptr, length + 16);

        for (int i = 0; i < 8; i++) {
            assertEquals(over[i], (byte) 0xFF);
            assertEquals(over[length + 8 + i], (byte) 0xFF);
        }
        for (int i = 0; i < p1.length; i++) {
            assertEquals(p1[i], p2[i]);
        }
    }

    protected byte[] getOverflow(int length) {
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            b[i] = (byte) 0xFF;
        }
        return b;
    }

}
