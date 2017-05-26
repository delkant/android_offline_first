/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;

import com.rodrigodelcanto.mobile.types.Booleans;
import com.rodrigodelcanto.mobile.types.Classes;
import com.rodrigodelcanto.mobile.types.Lists;
import com.rodrigodelcanto.mobile.types.Maps;
import com.rodrigodelcanto.mobile.types.Objects;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author delkant
 */
public class MapBasedEntity extends MapBasedObject implements ServiceEntity {
  
  public MapBasedEntity() {
    super();
  }

  public MapBasedEntity(Map map) {
    super(map);
  }

  @Override
  public String getId() {
    return (String) Objects.firstNotNull(map.get("_id"), map.get("id"));
  }

  public void setId(String id) {
    map.put("_id", id);
  }

  @Override
  public String getRevision() {
    return (String) map.get("_rev");
  }

  public void setRevision(String rev) {
    map.put("_rev", rev);
  }

  @Override
  public boolean isDeleted() {
    return Booleans.isTrue(map.get("_deleted"));
  }

  public void setDeleted(boolean deleted) {
    map.put("_deleted", deleted);
  }

  // attachments
  @Override
  public Map exportMap() {
    Map exported = new HashMap(map);
    exported.put("id", exported.get("_id"));
    Maps.removeAll(exported, "_.*");

    return exported;
  }

  public void load(Map m) {
    map.putAll(m);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof MapBasedEntity)) {
      return false;
    }

    String remoteId = ((MapBasedEntity) o).getId();
    String localId = this.getId();

    if (localId == null || remoteId == null) {
      return false;
    }

    return localId.equals(remoteId);
  }

  @Override
  public int hashCode() {
    if (getId() == null) {
      return 0;
    }

    return getId().hashCode();
  }

  public static <T extends MapBasedEntity> List<T> wrapList(Class<T> type, List maps) {
    List<T> list = new LinkedList<T>();

    if (!Lists.isEmpty(maps)) {
      for (Object i : maps) {
        T t = Classes.newInstance(type, type);
        t.load((Map) i);
        list.add(t);
      }
    }

    return list;
  }

  public static MapBasedEntity wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new MapBasedEntity((Map) o);
    } else if (o instanceof MapBasedEntity) {
      return new MapBasedEntity(((MapBasedEntity) o).internalMap());
    }

    return new MapBasedEntity();
  }

  public static <T extends MapBasedEntity> List<Map> exportList(List<T> entities) {
    List<Map> list = new LinkedList<Map>();

    if (!Lists.isEmpty(entities)) {
      for (T i : entities) {
        list.add(i.exportMap());
      }
    }

    return list;
  }

  public static <T extends MapBasedEntity> List<Map> exportList(Class<T> type, List maps) {
    List<Map> list = new LinkedList<Map>();

    if (!Lists.isEmpty(maps)) {
      for (Object i : maps) {
        T t = Classes.newInstance(type, type);
        t.load((Map) i);
        list.add(t.exportMap());
      }
    }

    return list;
  }

}
