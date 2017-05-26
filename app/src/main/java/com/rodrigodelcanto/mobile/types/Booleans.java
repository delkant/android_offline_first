/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.types;

/**
 *
 * @author delkant
 */
public final class Booleans {

  public static boolean isTrue(Object o) {
    if (o == null) {
      return false;
    }

    if (o instanceof Boolean) {
      return (Boolean) o;
    }

    if (o instanceof String) {
      return Boolean.parseBoolean((String) o);
    }

    return false;
  }

}
