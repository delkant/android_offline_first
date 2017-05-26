/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.people.document.geo;


import com.rodrigodelcanto.mobile.pojo.EntityRef;
import com.rodrigodelcanto.mobile.pojo.LogStamp;
import com.rodrigodelcanto.mobile.pojo.MapBasedEntity;
import com.rodrigodelcanto.mobile.pojo.ServicePrincipal;
import com.rodrigodelcanto.mobile.types.Booleans;
import com.rodrigodelcanto.people.document.contact.ContactInfo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author delkant
 */
public class Place extends MapBasedEntity {

  public final static String SCHEMA = "person:place";

  public Place() {
    super();
  }

  public Place(Map map) {
    super(map);
  }

  public String getSchema() {
    return (String) map.get("schema");
  }

  public void setSchema(String schema) {
    map.put("schema", SCHEMA);
  }

  public String getState() {
    return (String) map.get("state");
  }

  public void setState(String state) {
    map.put("state", state);
  }

  public String getName() {
    return (String) map.get("name");
  }

  public void setName(String name) {
    map.put("name", name);
  }

  public String getCode() {
    return (String) map.get("code");
  }

  public void setCode(String code) {
    map.put("code", code);
  }

  public Address getAddress() {
    return Address.wrap(map.get("address"));
  }

  public void setAddress(Address address) {
    putInternalMap(map, "address", address);
  }

  public EntityRef getPerson() {
    return EntityRef.wrap(map.get("person"));
  }

  public void setPerson(EntityRef person) {
    putInternalMap(map, "person", person);
  }

  public EntityRef getHeadquarter() {
    return EntityRef.wrap(map.get("headquarter"));
  }

  public void setHeadquarter(EntityRef headquarter) {
    setHead(headquarter.getCode().equals(getCode()));
    putInternalMap(map, "headquarter", headquarter);
  }

  public ContactInfo getContactInfo() {
    return ContactInfo.wrap(map.get("contactInfo"));
  }

  public void setContactInfo(ContactInfo contactInfo) {
    putInternalMap(map, "contactInfo", contactInfo);
  }

  public List getTags() {
    return (List) map.get("tags");
  }

  public void setTags(List tags) {
    map.put("tags", tags);
  }

  public boolean isHead() {
    return Booleans.isTrue(map.get("head"));
  }

  public void setHead(boolean head) {
    map.put("head", head);
  }

  public boolean isEnabled() {
    return Booleans.isTrue(map.get("enabled"));
  }

  public void setEnabled(boolean enabled) {
    map.put("enabled", enabled);
  }

  public LogStamp getInserted() {
    return LogStamp.wrap(map.get("inserted"));
  }

  public void setInserted(LogStamp stamp) {
    putInternalMap(map, "inserted", stamp);
  }

  public LogStamp getModified() {
    return LogStamp.wrap(map.get("modified"));
  }

  public void setModified(LogStamp stamp) {
    putInternalMap(map, "modified", stamp);
  }

  public EntityRef toEntityRef() {
    return EntityRef.newObject(getId(), getName(), getCode());
  }

  public static Place newEntity(ServicePrincipal principal, EntityRef person, String code) {
    Place entity = new Place();

    entity.setId(newId(person.getCode(), code));
    entity.setPerson(person);
    entity.setCode(code);
    entity.setSchema(SCHEMA);
    entity.setEnabled(true);
    entity.setInserted(LogStamp.newObject(principal.getPrincipal()));
    entity.setModified(LogStamp.newObject(principal.getPrincipal()));
    return entity;
  }

  public static Place wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new Place((Map) o);
    } else if (o instanceof Place) {
      return new Place(((Place) o).internalMap());
    }

    return new Place();
  }

  public static String newId(String code, String id) {
    return SCHEMA + "/" + code + "/" + id;
  }
}
