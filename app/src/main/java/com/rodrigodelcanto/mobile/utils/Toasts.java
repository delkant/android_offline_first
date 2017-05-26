package com.rodrigodelcanto.mobile.utils;

import android.content.Context;
import android.widget.Toast;

import com.rodrigodelcanto.people.R;

import java.util.Map;

/**
 * Created by delkant
 */

public class Toasts {

  public static void showShort(Context context, int messageKey) {
    String text = context.getString(messageKey);
    showShort(context, text);
  }

  public static void showShort(Context context, String text) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
  }

  public static void showLong(Context context, int messageKey) {
    String text = context.getString(messageKey);
    showLong(context, text);
  }

  public static void showLong(Context context, String text) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
  }

  public static void showResponseNOk(Context context, Map errorMap) {
    String msg = (String) errorMap.get("text");

    if(msg == null) {
      msg = context.getString(R.string.err_response_nok) + " " + errorMap.get("code");
    }

    showLong(context, msg);
  }
}
