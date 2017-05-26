package com.rodrigodelcanto.mobile.utils;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

/**
 * Created by delkant
 */

public class Activities {

  public static Intent replace(Activity activity, Class<? extends Activity> activityClass) {
    Intent intent = new Intent(activity, activityClass);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    activity.startActivity(intent);
    activity.finish();

    return intent;
  }

  public static Intent open(Activity activity, Class<? extends Activity> activityClass) {
    return open(activity, activityClass, null);
  }

  public static Intent open(Activity activity,
                            Class<? extends Activity> activityClass,
                            Serializable data) {

    Intent intent = new Intent(activity, activityClass);
    if (data != null) {
      intent.putExtra("data", data);
    }
    activity.startActivity(intent);
    return intent;
  }

  public static Intent open(Activity activity,
                            Class<? extends Activity> activityClass,
                            int requestCode) {
    return open(activity, activityClass, null, requestCode);
  }

  public static Intent open(Activity activity,
                            Class<? extends Activity> activityClass,
                            Serializable data,
                            int requestCode) {

    Intent intent = new Intent(activity, activityClass);
    if (data != null) {
      intent.putExtra("data", data);
    }

    activity.startActivityForResult(intent, requestCode);
    return intent;
  }

}
