/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.types;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author delkant
 */
public class Classes {

  private static final Logger logger = Logger.getLogger(Classes.class.getName());

  public static Class find(String className) {
    try {
      return Class.forName(className);

    } catch (ClassNotFoundException ex) {
      logger.log(Level.SEVERE, "class not found\n" + className, ex);
      return null;
    }
  }

  public static <T> T newInstance(String cn, Class<T> type) {
    return newInstance(find(cn), type);
  }

  public static <T> T newInstance(Class c, Class<T> type) {
    return (T) newInstance(c);
  }

  public static Object newInstance(Class c) {
    try {
      if (c == null) {
        return null;
      }

      return c.newInstance();

    } catch (InstantiationException ex) {
      logger.log(Level.SEVERE, "can't instantiate", ex);
      return null;
    } catch (IllegalAccessException ex) {
      logger.log(Level.SEVERE, "can't instantiate", ex);
      return null;
    }
  }

}
