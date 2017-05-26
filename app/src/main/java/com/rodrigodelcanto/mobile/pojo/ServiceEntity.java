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
public interface ServiceEntity {

  public String getId();

  public String getRevision();

  public Map internalMap();

  public Map exportMap();

  public boolean isDeleted();
}
