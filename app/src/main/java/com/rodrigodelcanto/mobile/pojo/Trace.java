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
public class Trace extends MapBasedObject {

  public Trace() {
    super();
  }

  public Trace(Map map) {
    super(map);
  }

  public String getPhase() {
    return (String) map.get("phase");
  }

  public void setPhase(String phase) {
    this.map.put("phase", phase);
  }

  public String getService() {
    return (String) map.get("service");
  }

  public void setService(String service) {
    this.map.put("service", service);
  }

  public String getNode() {
    return (String) map.get("node");
  }

  public void setNode(String node) {
    this.map.put("node", node);
  }

  public String getLocalAddr() {
    return (String) map.get("local_addr");
  }

  public void setLocalAddr(String localAddr) {
    this.map.put("local_addr", localAddr);
  }

  public int getLocalPort() {
    return (Integer) map.get("local_port");
  }

  public void setLocalPort(int localPort) {
    this.map.put("local_port", localPort);
  }

  public String getRemoteAddr() {
    return (String) map.get("remote_addr");
  }

  public void setRemoteAddr(String remoteAddr) {
    this.map.put("remote_addr", remoteAddr);
  }

  public int getRemotePort() {
    return (Integer) map.get("remote_port");
  }

  public void setRemotePort(int remotePort) {
    this.map.put("remote_port", remotePort);
  }

  public static Trace wrap(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof Map) {
      return new Trace((Map) o);
    } else if (o instanceof Trace) {
      return new Trace(((Trace) o).internalMap());
    }

    return new Trace();
  }
}
