/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;


import com.rodrigodelcanto.mobile.types.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author delkant
 */
public class EntityRef extends MapBasedObject {

  public EntityRef() {
    super();
  }

  public EntityRef(Map map) {
    super(map);
  }

  public String getRef() {
    return (String) map.get("ref");
  }

  public void setRef(String ref) {
    map.put("ref", ref);
  }

  public String getLabel() {
    return (String) map.get("label");
  }

  public void setLabel(String label) {
    map.put("label", label);
  }

  public String getCode() {
    return (String) map.get("code");
  }

  public void setCode(String code) {
    map.put("code", code);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof EntityRef) || getRef() == null) {
      return false;
    }

    return getRef().equals(((EntityRef) o).getRef());
  }

  /* statics */

 /* */
  public static EntityRef newObject(MapBasedObject mbo) {
    return newObject(mbo.internalMap());
  }

  public static EntityRef newObject(Map m) {
    if (m == null) {
      return null;
    }

    Map internalMap = new HashMap();
    Maps.copy(m, internalMap, "ref", "label", "code");

    if (internalMap.isEmpty()) {
      return null;
    }

    return new EntityRef(internalMap);
  }

  public static EntityRef newObject(String ref, String label, String code) {
    EntityRef o = new EntityRef();
    o.setRef(ref);
    o.setLabel(label);
    o.setCode(code);
    return o;
  }

  public static EntityRef wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new EntityRef((Map) o);
    } else if (o instanceof EntityRef) {
      return new EntityRef(((EntityRef) o).internalMap());
    }

    return new EntityRef();
  }

  @Override
  public int hashCode() {
    if (getRef() == null) {
      return 0;
    }
    return getRef().hashCode();
  }

}
