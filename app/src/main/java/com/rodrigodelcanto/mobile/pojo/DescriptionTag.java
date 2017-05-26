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
public class DescriptionTag extends MapBasedObject {

  public DescriptionTag() {
    super();
  }

  public DescriptionTag(Map map) {
    super(map);
  }

  public String getName() {
    return (String) map.get("name");
  }

  public void setName(String name) {
    this.map.put("name", name);
  }

  public String getLabel() {
    return (String) map.get("label");
  }

  public void setLabel(String label) {
    this.map.put("label", label);
  }

  public String getDescription() {
    return (String) map.get("description");
  }

  public void setDescription(String description) {
    this.map.put("description", description);
  }

  public NameTag toNameTag() {
    return NameTag.newObject(getName(), getLabel());
  }

  public static DescriptionTag newObject(String name) {
    return newObject(name, name);
  }

  public static DescriptionTag newObject(String name, String label) {
    return newObject(name, label, null);
  }

  public static DescriptionTag newObject(String name, String label, String description) {
    DescriptionTag stamp = new DescriptionTag();
    stamp.setName(name);
    stamp.setLabel(label);
    stamp.setDescription(description);
    return stamp;
  }

  public static DescriptionTag wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new DescriptionTag((Map) o);
    } else if (o instanceof DescriptionTag) {
      return new DescriptionTag(((DescriptionTag) o).internalMap());
    }

    return new DescriptionTag();
  }

}
