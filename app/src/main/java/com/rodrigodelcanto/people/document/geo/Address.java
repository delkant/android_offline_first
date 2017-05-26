/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.people.document.geo;


import com.rodrigodelcanto.mobile.pojo.EntityRef;
import com.rodrigodelcanto.mobile.pojo.Geolocation;
import com.rodrigodelcanto.mobile.pojo.MapBasedObject;

import java.util.Map;

/**
 *
 * @author delkant
 */
public class Address extends MapBasedObject {

  public Address() {
    super();
  }

  public Address(Map map) {
    super(map);
  }

  public EntityRef getCountry() {
    return EntityRef.wrap(map.get("country"));
  }

  public void setCountry(EntityRef country) {
    putInternalMap(map, "country", country);
  }

  public EntityRef getDepartment() {
    return EntityRef.wrap(map.get("department"));
  }

  public void setDepartment(EntityRef department) {
    putInternalMap(map, "department", department);
  }

  public EntityRef getDistrict() {
    return EntityRef.wrap(map.get("district"));
  }

  public void setDistrict(EntityRef district) {
    putInternalMap(map, "district", district);
  }

  public String getComunity() {
    return (String) map.get("comunity");
  }

  public void setComunity(String comunity) {
    map.put("comunity", comunity);
  }

  public String getAddress() {
    return (String) map.get("address");
  }

  public void setAddress(String address) {
    map.put("address", address);
  }

  public String getDescription() {
    return (String) map.get("description");
  }

  public void setDescription(String description) {
    map.put("description", description);
  }

  public Geolocation getLocation() {
    return Geolocation.wrap(map.get("location"));
  }

  public void setLocation(Geolocation location) {
    putInternalMap(map, "location", location);
  }

  public static Address newObject() {
    return new Address();
  }

  public static Address wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new Address((Map) o);
    } else if (o instanceof Address) {
      return new Address(((Address) o).internalMap());
    }

    return new Address();
  }

}
