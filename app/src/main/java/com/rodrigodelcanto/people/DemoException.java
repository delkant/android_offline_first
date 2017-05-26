package com.rodrigodelcanto.people;

import android.view.View;

/**
 * Created by delkant
 */

public class DemoException extends Exception {

  public DemoException(String message) {
    super(message);
  }

  public DemoException(String message, Throwable cause) {
    super(message, cause);
  }

  public DemoException(String message, View view) {
    super(message);
    this.view = view;
  }

  public DemoException(View view) {
    super();
    this.view = view;
  }

  private View view;

  public View getView() {
    return view;
  }

  public void setView(View view) {
    this.view = view;
  }

}
