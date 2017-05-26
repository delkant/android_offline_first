package com.rodrigodelcanto.mobile.commons.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by delkant on 11/10/16.
 */

public class EntryData {
  protected String id;
  protected String name;
  protected String shortName;
  protected Map<String, Object> properties;
  protected boolean editable = false;

  public EntryData() {
  }

  public EntryData(String id,
                   String name,
                   String shortName,
                   Map<String, Object> properties,
                   boolean editable) {
    this.id = id;
    this.name = name;
    this.shortName = shortName;
    this.properties = properties;
    this.editable = editable;
  }

  public EntryData(String id, String name, boolean editable) {
    this(id, name, null, new HashMap<String, Object>(), editable);
  }

  public EntryData(String id, String name, String shortName, Map<String, Object> properties) {
    this(id, name, shortName, properties, false);
  }

  public static String getLabel(String name, String shortName) {
    return (name.equals(shortName)) ? name : name + " (" + shortName + ")";
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getShortName() {
    return (shortName != null) ? shortName : name;
  }

  public String getLabel() {
    return getLabel(getName(), getShortName());
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public boolean isEditable() {
    return editable;
  }
}
