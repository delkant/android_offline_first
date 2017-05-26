package com.rodrigodelcanto.mobile.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.TextView;

/**
 * Created by delkant
 */

public class Views {

  public static void focus(View view) {
    if (view != null) {
      view.requestFocus();
    }
  }

  public static void setError(View view, String errorText) {
    setError(view, errorText, null);
  }

  public static void setError(View view, String errorText, Drawable icon) {
    if (view != null) {

      if (view instanceof TextView) {
        if (icon == null) {
          ((TextView) view).setError(errorText);
        } else {
          ((TextView) view).setError(errorText, icon);
        }
      }

      view.requestFocus();
    }
  }

  public static String getText(View from, int textViewId) {
    TextView textView = (TextView) from.findViewById(textViewId);
    if(textView != null && textView.getText() != null) {
      return textView.getText().toString();
    }

    return null;
  }

  public static void setText(View from, int textViewId, String text) {
    TextView textView = (TextView) from.findViewById(textViewId);
    if(textView != null) {
      textView.setText(text);
    }
  }

  public static void setContentView(View from, int insertPointId, View contentView) {
    ViewGroup insertPoint = (ViewGroup) from.findViewById(insertPointId);
    if(insertPoint != null) {
      insertPoint.removeAllViews();
      insertPoint.addView(contentView);
    }
  }

  public static void addView(View from, int insertPointId, View newLastChild) {
    ViewGroup insertPoint = (ViewGroup) from.findViewById(insertPointId);
    if(insertPoint != null) {
      //insertPoint.removeAllViews();
      insertPoint.addView(newLastChild);
    }
  }

  public static void removeView(View from, int viewId) {
    View v = from.findViewById(viewId);
    if(v != null) {
      removeView(v);
    }
  }

  public static void removeView(View v) {
    ((ViewManager) v.getParent()).removeView(v);
  }

}
