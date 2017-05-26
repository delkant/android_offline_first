/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author delkant
 */
public class Geolocation extends MapBasedObject {

  private Date ts;

  public Geolocation() {
    super();
  }

  public Geolocation(Map map) {
    super(map);
  }

  public static Geolocation newObject(BigDecimal latitude, BigDecimal longitude) {
    Geolocation o = new Geolocation();
    o.setLatitude(latitude);
    o.setLongitude(longitude);
    return o;
  }

  public static Geolocation wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new Geolocation((Map) o);
    } else if (o instanceof Geolocation) {
      return new Geolocation(((Geolocation) o).internalMap());
    }

    return new Geolocation();
  }

  public BigDecimal getLatitude() {
    String s = (String) map.get("latitude");
    if (s == null) {
      s = "0";
    }
    return new BigDecimal(s);
  }

  public void setLatitude(BigDecimal latitude) {
    this.map.put("latitude", latitude == null ? "0" : latitude.toString());
  }

  public BigDecimal getLongitude() {
    String s = (String) map.get("longitude");
    if (s == null) {
      s = "0";
    }
    return new BigDecimal(s);
  }

  public void setLongitude(BigDecimal longitude) {
    this.map.put("longitude", longitude == null ? "0" : longitude.toString());
  }

}
