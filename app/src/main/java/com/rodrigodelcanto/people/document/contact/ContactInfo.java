/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.people.document.contact;


import com.rodrigodelcanto.mobile.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * TODO: signature,
 *
 *
 * @author delkant
 */
public class ContactInfo extends MapBasedObject {

  public ContactInfo() {
    super();
  }

  public ContactInfo(Map map) {
    super(map);
  }

  public List<Phone> getPhones() {
    return getList(map, "phones", Phone.class);
  }

  public void setPhones(List<Phone> phones) {
    putList(map, "phones", phones);
  }

  public List<Email> getEmails() {
    return getList(map, "emails", Email.class);
  }

  public void setEmails(List<Email> emails) {
    putList(map, "emails", emails);
  }

  public String getWebsite() {
    return (String) map.get("website");
  }

  public void setWebsite(String website) {
    map.put("website", website);
  }

  public String getExtra() {
    return (String) map.get("extra");
  }

  public void setExtra(String extra) {
    map.put("extra", extra);
  }

  public static ContactInfo newObject() {
    return new ContactInfo();
  }

  public static ContactInfo wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new ContactInfo((Map) o);
    } else if (o instanceof ContactInfo) {
      return new ContactInfo(((ContactInfo) o).internalMap());
    }

    return new ContactInfo();
  }

}
