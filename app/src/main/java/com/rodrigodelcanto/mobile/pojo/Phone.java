/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;


import com.rodrigodelcanto.mobile.types.Booleans;

import java.util.Map;

/**
 *
 * @author delkant
 */
public class Phone extends MapBasedObject {

  public Phone() {
    super();
  }

  public Phone(Map map) {
    super(map);
  }

  public String getPhone() {
    return (String) map.get("phone");
  }

  public void setPhone(String phone) {
    map.put("phone", phone);
  }

  public boolean isMobile() {
    return Booleans.isTrue(map.get("mobile"));
  }

  public void setMobile(boolean mobile) {
    map.put("mobile", mobile);
  }

  public boolean isWhatsappEnabled() {
    return Booleans.isTrue(map.get("whatsappEnabled"));
  }

  public void setWhatsappEnabled(boolean whatsappEnabled) {
    map.put("whatsappEnabled", whatsappEnabled);
  }

  public static Phone newObject(String phone) {
    Phone o = new Phone();

    o.setPhone(phone);
    return o;
  }

  public static Phone wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new Phone((Map) o);
    } else if (o instanceof Phone) {
      return new Phone(((Phone) o).internalMap());
    }

    return new Phone();
  }
}
