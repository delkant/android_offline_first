/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.types;

import android.util.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author delkant
 */
public final class Strings {

  private static final Logger LOG = Logger.getLogger(Strings.class.getName());

  public static String md5(String s) {
    try {
      byte[] bytes = MessageDigest.getInstance("MD5").digest(s.getBytes());
      return String.format("%032x", new BigInteger(1, bytes));

      /*import javax.xml.bind.DatatypeConverter;
        return DatatypeConverter.printHexBinary(bytes);
       */
    } catch (NoSuchAlgorithmException ex) {
      LOG.log(Level.SEVERE, null, ex);
      return null;
    }
  }

  public static String encodeBase64(String s) {
    return Base64.encodeToString(s.getBytes(), Base64.NO_WRAP);
  }

  public static String encodeBase64(byte[] bytes) {
    return Base64.encodeToString(bytes, Base64.NO_WRAP);
  }

  public static String decodeBase64(String base64) {
    return new String(Base64.decode(base64, Base64.NO_WRAP));
  }

  public static byte[] decodeBase64ToBytes(String base64) {
    return Base64.decode(base64, Base64.NO_WRAP);
  }

  public static boolean isEmpty(String s) {
    return isEmpty(s, true);
  }

  public static boolean isEmpty(String s, boolean trimed) {
    return s == null || (trimed ? s.trim().isEmpty() : s.isEmpty());
  }

  public static String trim(String s) {
    return s == null ? "" : s.trim();
  }

  public static String trim(String s, String def) {
    return s == null ? def : s.trim();
  }

  public static String randomId() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  public static int count(String s, char c) {
    if (isEmpty(s)) {
      return 0;
    }

    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == c) {
        count++;
      }
    }

    return count;
  }

}
