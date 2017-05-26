/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;

import java.util.Map;

/**
 *
 * @author delkant
 *
 * */
public class NameTag extends MapBasedObject {

  public NameTag() {
    super();
  }

  public NameTag(Map map) {
    super(map);
  }

  public String getName() {
    return (String) map.get("name");
  }

  public void setName(String name) {
    this.map.put("name", name);
  }

  public String getLabel() {
    return (String) map.get("label");
  }

  public void setLabel(String label) {
    this.map.put("label", label);
  }

  public static NameTag newObject(String name) {
    return newObject(name, name);
  }

  public static NameTag newObject(String name, String label) {
    NameTag o = new NameTag();
    o.setName(name);
    o.setLabel(label);
    return o;
  }

  public static NameTag wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new NameTag((Map) o);
    } else if (o instanceof NameTag) {
      return new NameTag(((NameTag) o).internalMap());
    }

    return new NameTag();
  }

}
