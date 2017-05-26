/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;


import com.rodrigodelcanto.mobile.types.Objects;

import java.util.Map;

/**
 * @author delkant
 */
public class CodeTag extends MapBasedObject {

  public CodeTag() {
    super();
  }

  public CodeTag(Map map) {
    super(map);
  }

  public static CodeTag newObject(String name) {
    return newObject(name, name);
  }

  public static CodeTag newObject(String code, String label) {
    CodeTag o = new CodeTag();
    o.setCode(code);
    o.setLabel(label);
    return o;
  }

  public static CodeTag wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new CodeTag((Map) o);
    } else if (o instanceof CodeTag) {
      return new CodeTag(((CodeTag) o).internalMap());
    }

    return new CodeTag();
  }

  public String getCode() {
    return (String) map.get("code");
  }

  public void setCode(String code) {
    this.map.put("code", code);
  }

  public String getLabel() {
    return (String) map.get("label");
  }

  public void setLabel(String label) {
    this.map.put("label", label);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof CodeTag)) {
      return false;
    }

    return Objects.entityEquals(this.getCode(), ((CodeTag) o).getCode());
  }

  @Override
  public int hashCode() {
    return getCode().hashCode();
  }
}
