/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.types;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author delkant
 */
public final class Times {

  private static final Logger LOG = Logger.getLogger(Times.class.getName());

  public static final SimpleDateFormat fullglued = new SimpleDateFormat("yyyyMMddHHmmssSSS");
  public static final SimpleDateFormat tsglued = new SimpleDateFormat("yyyyMMddHHmmss");
  public static final SimpleDateFormat fullf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  public static final SimpleDateFormat tsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  public static final SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");

  public static Date now() {
    return new Date();
  }

  public static String date() {
    return datef.format(now());
  }

  public static String date(Date d) {
    return datef.format(d);
  }

  public static String ts() {
    return tsf.format(now());
  }

  public static String ts(Date d) {
    return tsf.format(d);
  }

  public static String full() {
    return fullf.format(now());
  }

  public static String full(Date d) {
    return fullf.format(d);
  }

  public static String tsGlued() {
    return tsglued.format(now());
  }

  public static String tsGlued(Date d) {
    return tsglued.format(d);
  }

  public static String fullGlued() {
    return fullglued.format(now());
  }

  public static String fullGlued(Date d) {
    return fullglued.format(d);
  }

  public static Date readDate(String s) {
    try {
      return Strings.isEmpty(s) ? null : datef.parse(s);

    } catch (ParseException ex) {
      LOG.log(Level.SEVERE, null, ex);
      return null;
    }
  }

  public static Date readTs(String s) {
    try {
      return Strings.isEmpty(s) ? null : tsf.parse(s);

    } catch (ParseException ex) {
      LOG.log(Level.SEVERE, null, ex);
      return null;
    }
  }

  public static Date readFull(String s) {
    try {
      return Strings.isEmpty(s) ? null : fullf.parse(s);

    } catch (ParseException ex) {
      LOG.log(Level.SEVERE, null, ex);
      return null;
    }
  }
}
