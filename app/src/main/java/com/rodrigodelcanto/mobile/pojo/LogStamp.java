/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.mobile.pojo;


import com.rodrigodelcanto.mobile.types.Times;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author delkant
 */
public class LogStamp extends MapBasedObject {

  private Date ts;

  public LogStamp() {
    super();
  }

  public LogStamp(Map map) {
    super(map);
  }

  public String getPrincipal() {
    return (String) map.get("principal");
  }

  public void setPrincipal(String principal) {
    this.map.put("principal", principal);
  }

  public Date getTimestamp() {
    if (ts != null) {
      return ts;
    }

    if (!map.containsKey("timestamp")) {
      return null;
    }

    ts = Times.readTs((String) map.get("timestamp"));
    map.put("millis", ts.getTime());

    return ts;
  }

  public void setTimestamp(Date date) {
    ts = date;
    if (ts != null) {
      map.put("timestamp", Times.ts(ts));
      map.put("millis", ts.getTime());
    }
  }

  public long getMillis() {
    if (map.containsKey("millis")) {
      return (Long) map.get("millis");
    }

    Date d = getTimestamp();
    return d == null ? 0 : d.getTime();
  }

  public void setMillis(long millis) {
    ts = new Date(millis);
    if (ts != null) {
      map.put("millis", millis);
    }
  }

  public String toTimeString() {
    return Times.ts(getTimestamp());
  }

  public static LogStamp newObject(String principal) {
    LogStamp stamp = new LogStamp();
    stamp.setPrincipal(principal);
    stamp.setTimestamp(new Date());
    return stamp;
  }

  public static LogStamp wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new LogStamp((Map) o);
    } else if (o instanceof LogStamp) {
      return new LogStamp(((LogStamp) o).internalMap());
    }

    return new LogStamp();
  }

}
