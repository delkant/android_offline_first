package com.rodrigodelcanto.mobile.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by delkant
 */

public class Inflaters {

  public static LayoutInflater inflater(Context context) {
    return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public static View inflate(Context context, int layout) {
    return inflater(context).inflate(layout, null);
  }

  public static View inflate(Context context, int layout, ViewGroup viewGroup) {
    return inflater(context).inflate(layout, viewGroup);
  }
}
