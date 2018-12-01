/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Juan Ferreira Carlos
 */
public final class Hash {
    
    private Hash() {
    }
    
    public static String md5(String source) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            hash = Base64.getEncoder().encodeToString(md.digest(source.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException  ex)  {
            throw new RuntimeException(ex);
        }
        return hash;
    }
    
}
