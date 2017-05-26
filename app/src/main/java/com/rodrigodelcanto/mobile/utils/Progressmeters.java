package com.rodrigodelcanto.mobile.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.view.View;

/**
 * Created by delkant
 */

public class Progressmeters {

  public static void toggleVisibility(final View progressView,
                                      final View otherView,
                                      final boolean visible,
                                      int animTime) {
    // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
    // for very easy animations. If available, use these APIs to fade-in
    // the progress spinner.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

      otherView.setVisibility(visible ? View.GONE : View.VISIBLE);
      otherView.animate().setDuration(animTime).alpha(
        visible ? 0 : 1).setListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          otherView.setVisibility(visible ? View.GONE : View.VISIBLE);
        }
      });

      progressView.setVisibility(visible ? View.VISIBLE : View.GONE);
      progressView.animate().setDuration(animTime).alpha(
        visible ? 1 : 0).setListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          progressView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
      });
    } else {
      // The ViewPropertyAnimator APIs are not available, so simply show
      // and hide the relevant UI components.
      progressView.setVisibility(visible ? View.VISIBLE : View.GONE);
      otherView.setVisibility(visible ? View.GONE : View.VISIBLE);
    }

  }
}
