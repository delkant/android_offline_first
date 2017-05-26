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
public class Base64Picture extends MapBasedObject {

  public Base64Picture() {
    super();
  }

  public Base64Picture(Map map) {
    super(map);
  }

  public String getCode() {
    return (String) map.get("code");
  }

  public void setCode(String code) {
    this.map.put("code", code);
  }

  public String getMimetype() {
    return (String) map.get("mimetype");
  }

  public void setMimetype(String mimetype) {
    this.map.put("mimetype", mimetype);
  }

  public String getBase64() {
    return (String) map.get("base64");
  }

  public void setBase64(String base64) {
    this.map.put("base64", base64);
  }

  public String getLabel() {
    return (String) map.get("label");
  }

  public void setLabel(String label) {
    this.map.put("label", label);
  }

  public static Base64Picture newObject(String code) {
    Base64Picture o = new Base64Picture();
    o.setCode(code);
    return o;
  }

  public static Base64Picture wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new Base64Picture((Map) o);
    } else if (o instanceof Base64Picture) {
      return new Base64Picture(((Base64Picture) o).internalMap());
    }

    return new Base64Picture();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Base64Picture)) {
      return false;
    }

    return Objects.entityEquals(this.getCode(), ((Base64Picture) o).getCode());
  }

  @Override
  public int hashCode() {
    return getCode().hashCode();
  }
}
