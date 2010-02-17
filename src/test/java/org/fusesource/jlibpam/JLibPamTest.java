package org.fusesource.jlibpam;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.fusesource.jlibpam.internal.PamLibrary;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JLibPamTest {

    @BeforeClass
    public static void prep() {
        try {
            URLClassLoader cl = (URLClassLoader) JLibPamTest.class.getClassLoader();
            Method mth = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            mth.setAccessible(true);
            mth.invoke(cl, new File("target/jlibpam-1.0-SNAPSHOT-osx64.jar").toURI().toURL());

            System.setProperty("library.jlibpam.path", "target/native-build/target/lib");

            JLibPamTest.class.getClassLoader().loadClass(PamLibrary.class.getName());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

	@Test
	public void test() {
		long[] plong = new long[1];
		PamLibrary.pam_conv conv = new PamLibrary.pam_conv();
		int rc = PamLibrary.pam_start("sshd", "servicemix", conv, plong);
		long pamh = plong[0];
		
		PamLibrary.pam_set_item(pamh, PamLibrary.PAM_AUTHTOK, "servicemix");
		rc = PamLibrary.pam_authenticate(pamh, 0);
	}
	
}