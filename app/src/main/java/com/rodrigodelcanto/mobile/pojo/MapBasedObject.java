/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;

import com.rodrigodelcanto.mobile.types.Classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author delkant
 */
public class MapBasedObject implements Serializable {

  protected final Map map;

  public MapBasedObject() {
    this.map = new HashMap();
  }

  public MapBasedObject(Map map) {
    this.map = new HashMap(map);
  }

  protected static Object getOrDefault(Map m, String key, Object defValue) {
    Object o = m.get(key);
    return o == null ? defValue : o;
  }

  protected static void putInternalMap(Map m, String key, MapBasedObject o) {
    if (o == null && m.containsKey(key)) {
      m.remove(key);
    } else if (o != null) {
      m.put(key, o.internalMap());
    }
  }

  protected static <T extends MapBasedObject> List<T> getList(Map m, String key, Class<T> tClass) {

    List<T> result = new LinkedList<>();

    if (m != null && key != null && tClass != null) {
      List list = (List) m.get(key);

      if (list != null) {
        for (Object item : list) {
          if (item != null && item instanceof Map) {
            T t = Classes.newInstance(tClass, tClass);
            t.load((Map) item);
            result.add(t);
          }
        }
      }
    }

    return result;
  }

  protected static <T extends MapBasedObject> void putList(Map m, String key, List<T> list) {
    if (m != null && key != null && list != null) {
      List<Map> mapList = new LinkedList<>();

      for (T t : list) {
        mapList.add(t.internalMap());
      }

      m.put(key, mapList);
    }
  }

  public Map internalMap() {
    return map;
  }

  public void load(Map m) {
    if (m != null) {
      map.putAll(m);
    }
  }

}
