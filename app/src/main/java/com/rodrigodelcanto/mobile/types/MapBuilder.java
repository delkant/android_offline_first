/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.types;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author delkant
 */
public class MapBuilder {

  private final Map m = new HashMap();

  public MapBuilder load(Map m) {
    m.putAll(m);
    return this;
  }

  public MapBuilder put(Object k, Object v) {
    m.put(k, v);
    return this;
  }

  public MapBuilder insert(String key, Object v) {
    Maps.insert(m, key, v);
    return this;
  }

  public MapBuilder copy(Map src, String srcpath, String insertion) {
    //XXX todo
    return this;
  }

  public Map toMap() {
    return m;
  }
}
