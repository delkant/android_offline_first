/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author delkant
 */
public class ServicePrincipal extends MapBasedObject {

  public static final String K_ID = "id";
  public static final String K_PRINCIPAL = "principal";
  public static final String K_ROLES = "roles";
  public static final String K_DATA = "data";

  public ServicePrincipal() {
    super();
  }

  public ServicePrincipal(Map m) {
    super(m);
  }

  public static ServicePrincipal wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new ServicePrincipal((Map) o);
    } else if (o instanceof ServicePrincipal) {
      return new ServicePrincipal(((ServicePrincipal) o).internalMap());
    }

    return new ServicePrincipal();
  }

  public String getId() {
    return (String) map.get(K_ID);
  }

  public void setId(String id) {
    map.put(K_ID, id);
  }

  public String getPrincipal() {
    return (String) map.get(K_PRINCIPAL);
  }

  public void setPrincipal(String principal) {
    map.put(K_PRINCIPAL, principal);
  }

  public List<String> getRoles() {
    List roles = (List) map.get(K_ROLES);
    return roles == null ? new LinkedList<>() : roles;
  }

  public void setRoles(List<String> roles) {
    map.put(K_ROLES, roles == null ? new LinkedList() : roles);
  }

  public Map getData() {
    return (Map) map.get(K_DATA);
  }

  public void setData(Map data) {
    map.put(K_DATA, data);
  }

  public Map toMap() {
    Map m = new HashMap();

    return m;
  }

}
