/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.types;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author delkant
 */
public final class Lists {

  public static boolean isEmpty(Collection c) {
    return c == null || c.isEmpty();
  }

  public static <T extends Collection> T ifEmpty(T c, T def) {
    return isEmpty(c) ? def : c;
  }

  public static <T> List<T> uniques(List<T> list) {
    if (isEmpty(list)) {
      return new LinkedList<T>();
    }

    return new LinkedList<T>(new HashSet<T>(list));
  }

}
