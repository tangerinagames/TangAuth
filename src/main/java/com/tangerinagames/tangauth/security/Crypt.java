package com.tangerinagames.tangauth.security;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {

    private static Crypt crypt;
    protected MessageDigest digest;

    private Crypt() throws NoSuchAlgorithmException {
        digest = MessageDigest.getInstance("MD5");
    }

    public static Crypt getInstance() throws NoSuchAlgorithmException {
        if (crypt == null) {
            crypt = new Crypt();
        }
        return crypt;
    }

    public String encrypt(String value) {
        return new BigInteger(1, digest.digest(value.getBytes())).toString(16);
    }

}
