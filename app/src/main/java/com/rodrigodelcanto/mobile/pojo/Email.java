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
 */
public class Email extends MapBasedObject {

  public Email() {
    super();
  }

  public Email(Map map) {
    super(map);
  }

  public String getAddress() {
    return (String) map.get("address");
  }

  public void setAddress(String address) {
    map.put("address", address);
  }

  public String getLabel() {
    return (String) map.get("label");
  }

  public void setLabel(String label) {
    map.put("label", label);
  }

  public static Email newObject(String address) {
    return newObject(address, address);
  }

  public static Email newObject(String address, String label) {
    Email o = new Email();

    o.setAddress(address);
    o.setLabel(label);
    return o;
  }

  public static Email wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new Email((Map) o);
    } else if (o instanceof Email) {
      return new Email(((Email) o).internalMap());
    }

    return new Email();
  }
}
