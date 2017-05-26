/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.types;

import java.util.logging.Logger;

/**
 *
 * @author delkant
 */
public class Objects {

  private static final Logger LOG = Logger.getLogger(Objects.class.getName());

  public static <T> boolean entityEquals(T o1, T o2) {
    return o1 != null && o2 != null && o1.equals(o2);
  }

  public static <T> T firstNotNull(T... objects) {
    for (T o : objects) {
      if (o != null) {
        return o;
      }
    }

    return null;
  }

}
