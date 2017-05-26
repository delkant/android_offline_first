/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author delkant
 */
public class Maps {

  private static final Logger LOG = Logger.getLogger(Maps.class.getName());

  public static boolean isEmpty(Map m) {
    return m == null || m.isEmpty();
  }

  public static Object find(Map m, String path) {
    if (m == null || path == null) {
      return null;
    }

    Map source = m;
    String[] keys = path.split("\\.");

    for (int i = 0; i < keys.length; i++) {
      Object value = source.get(keys[i]);

      if (keys.length == i + 1) {
        return value;

      } else if (value == null) {
        return null;

      } else if (value instanceof Map) {
        source = (Map) value;

      } else { // example: list value
        return null;
      }
    }

    return null;
  }

  public static List find(List<Map> sources, String path) {
    List list = new LinkedList();

    if (sources != null) {
      for (Map m : sources) {
        list.add(find(m, path));
      }
    }

    return list;
  }

  public static void insert(Map m, String path, Object value) {
    if (m == null || path == null) {
      return;
    }

    Map target = m;
    String[] keys = path.split("\\.");

    for (int i = 0; i < keys.length; i++) {
      if (keys.length == i + 1) { // last item
        target.put(keys[i], value);

      } else {
        Map child = new HashMap();
        target.put(keys[i], child);
        target = child;
      }
    }
  }

  public static void removeAll(Map m, String regex) {
    if (m == null || regex == null) {
      return;
    }

    Pattern pattern = Pattern.compile(regex);
    for (Object k : new HashSet(m.keySet())) {
      if (pattern.matcher(k.toString()).matches()) {
        m.remove(k);
      }
    }
  }

  public static void copy(List<Map> src, List<Map> dst, String... keys) {
    if (src != null && dst != null && !src.isEmpty()) {
      for (Map i : src) {
        Map out = new HashMap();
        copy(i, out, keys);
        dst.add(out);
      }
    }
  }

  public static void copy(Map src, Map dst, String... keys) {
    if (keys == null || keys.length == 0) {
      return;
    }

    for (String s : Arrays.asList(keys)) {
      if (src.containsKey(s)) {
        dst.put(s, src.get(s));
      }
    }
  }

  public static void copyBools(Map src, Map dst, String... keys) {
    if (keys == null || keys.length == 0) {
      return;
    }

    for (String s : Arrays.asList(keys)) {
      if (src.containsKey(s)) {
        dst.put(s, Booleans.isTrue(src.get(s)));
      }
    }
  }

  public static void copy(Map src, Collection dst, boolean cpNulls, String... keys) {
    if (src == null || src.isEmpty() || dst == null || dst.isEmpty() || keys == null) {
      return;
    }

    for (String k : keys) {
      if (src.containsKey(k)) {
        Object o = src.get(k);
        if (cpNulls || o != null) {
          dst.add(o);
        }
      }
    }
  }

}
