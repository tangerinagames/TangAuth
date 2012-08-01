package com.tangerinagames.tangauth.security;

import org.junit.Test;
import java.lang.reflect.Modifier;
import static org.junit.Assert.*;

public class CryptTest {

    @Test
    public void shouldBeASingleton() throws Exception {
        assertEquals(0, Crypt.class.getConstructors().length);
        assertTrue(Modifier.isStatic(Crypt.class.getDeclaredMethod("getInstance").getModifiers()));
    }

    @Test
    public void shouldUseMD5Algorithm() throws Exception {
        assertEquals("MD5", Crypt.getInstance().digest.getAlgorithm());
    }

    @Test
    public void shouldEncryptAValue() throws Exception {
        assertEquals("583080442fdc893f2410d88ccffce26f", Crypt.getInstance().encrypt("TangZero"));
    }

}
