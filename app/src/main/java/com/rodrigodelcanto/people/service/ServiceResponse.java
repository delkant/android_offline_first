package com.rodrigodelcanto.people.service;

import java.util.Map;

/**
 * Created by delkant
 */

public class ServiceResponse {

  private String id;
  private int response = 0;
  private Map error;
  private String context;
  private Object tracepath;
  private boolean tracing;
  private boolean test;
  private Map message;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map getMessage() {
    return message;
  }

  public void setMessage(Map message) {
    this.message = message;
  }

  public int getResponse() {
    return response;
  }

  public void setResponse(int response) {
    this.response = response;
  }

  public Map getError() {
    return error;
  }

  public void setError(Map error) {
    this.error = error;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public Object getTracepath() {
    return tracepath;
  }

  public void setTracepath(Object tracepath) {
    this.tracepath = tracepath;
  }

  public boolean isTracing() {
    return tracing;
  }

  public void setTracing(boolean tracing) {
    this.tracing = tracing;
  }

  public boolean isTest() {
    return test;
  }

  public void setTest(boolean test) {
    this.test = test;
  }

  public boolean isOk() {
    return response == 0;
  }

}
