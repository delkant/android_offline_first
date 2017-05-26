/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;


import com.rodrigodelcanto.mobile.types.Objects;

import java.util.Map;

/**
 *
 * @author delkant
 */
public class KeyValue extends MapBasedObject {

  public KeyValue() {
    super();
  }

  public KeyValue(Map map) {
    super(map);
  }

  public String getKey() {
    return (String) map.get("key");
  }

  public void setKey(String key) {
    this.map.put("key", key);
  }

  public String getValue() {
    return (String) map.get("value");
  }

  public void setValue(String value) {
    this.map.put("value", value);
  }

  public static KeyValue newObject(String name) {
    return newObject(name, name);
  }

  public static KeyValue newObject(String key, String value) {
    KeyValue o = new KeyValue();
    o.setKey(key);
    o.setValue(value);
    return o;
  }

  public static KeyValue wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new KeyValue((Map) o);
    } else if (o instanceof KeyValue) {
      return new KeyValue(((KeyValue) o).internalMap());
    }

    return new KeyValue();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof KeyValue)) {
      return false;
    }

    return Objects.entityEquals(this.getKey(), ((KeyValue) o).getKey());
  }

  @Override
  public int hashCode() {
    return getKey().hashCode();
  }
}
