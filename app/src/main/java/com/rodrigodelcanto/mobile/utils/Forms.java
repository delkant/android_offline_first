package com.rodrigodelcanto.mobile.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.rodrigodelcanto.people.R;
import java.util.Map;

/**
 * Created by delkant
 */

public class Forms {

  public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
  public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

  /* ::: linear layout ::: */

  public static LinearLayout newLinear(Context ctxt) {
    return newLinear(ctxt, true, false, 0f);
  }

  public static LinearLayout newLinear(Context ctxt,
                                       boolean widthAll,
                                       boolean heightAll,
                                       float weight) {

    int width = widthAll ? MATCH_PARENT : WRAP_CONTENT;
    int height = heightAll ? MATCH_PARENT : WRAP_CONTENT;

    LinearLayout.LayoutParams params;
    if (weight > 0) {
      params = new LinearLayout.LayoutParams(width, height, weight);
    } else {
      params = new LinearLayout.LayoutParams(width, height);
    }

    LinearLayout layout = new LinearLayout(ctxt);
    layout.setLayoutParams(params);
    return layout;
  }

  /* ::: text layout ::: */

  public static TextInputLayout newTextLayout(Context ctxt) {
    return newTextLayout(ctxt, true, false, 0f);
  }

  public static TextInputLayout newTextLayout(Context ctxt,
                                              boolean widthAll,
                                              boolean heightAll,
                                              float weight) {

    int width = widthAll ? MATCH_PARENT : WRAP_CONTENT;
    int height = heightAll ? MATCH_PARENT : WRAP_CONTENT;

    TextInputLayout.LayoutParams params;
    if (weight > 0) {
      params = new TextInputLayout.LayoutParams(width, height, weight);
    } else {
      params = new TextInputLayout.LayoutParams(width, height);
    }

    TextInputLayout layout = new TextInputLayout(ctxt);
    layout.setLayoutParams(params);
    return layout;
  }

  /* ::: edit text ::: */

  public static EditText newEditText(Context ctxt) {
    return newEditText(ctxt, true, false, 0f);
  }

  public static EditText newEditText(Context ctxt,
                                     boolean widthAll,
                                     boolean heightAll,
                                     float weight) {

    int width = widthAll ? MATCH_PARENT : WRAP_CONTENT;
    int height = heightAll ? MATCH_PARENT : WRAP_CONTENT;

    TextInputLayout.LayoutParams params;
    if (weight > 0) {
      params = new TextInputLayout.LayoutParams(width, height, weight);
    } else {
      params = new TextInputLayout.LayoutParams(width, height);
    }

    EditText widget = new EditText(ctxt);
    widget.setLayoutParams(params);
    return widget;
  }


  /* ::: button ::: */

  public static Button newButton(Context ctxt, int label) {
    return newButton(ctxt, label, true, false, 0f);
  }

  public static Button newButton(Context ctxt, String label) {
    return newButton(ctxt, label, true, false, 0f);
  }

  public static Button newButton(Context ctxt,
                                 int label,
                                 boolean widthAll,
                                 boolean heightAll,
                                 float weight) {
    return newButton(ctxt, ctxt.getString(label), widthAll, heightAll, weight);
  }

  public static Button newButton(Context ctxt,
                                 String label,
                                 boolean widthAll,
                                 boolean heightAll,
                                 float weight) {

    int width = widthAll ? MATCH_PARENT : WRAP_CONTENT;
    int height = heightAll ? MATCH_PARENT : WRAP_CONTENT;

    LinearLayout.LayoutParams params;
    if (weight > 0) {
      params = new LinearLayout.LayoutParams(width, height, weight);
    } else {
      params = new LinearLayout.LayoutParams(width, height);
    }

    Button widget = new Button(ctxt);
    widget.setText(label);
    widget.setLayoutParams(params);
    return widget;
  }

  /* ::: complex objects ::: */

  @RequiresApi(api = Build.VERSION_CODES.M)
  public static LinearLayout newRow(Context ctxt, String text, final Map ref) {
    final LinearLayout linearLayout = Forms.newLinear(ctxt);
    linearLayout.setOrientation(LinearLayout.HORIZONTAL);

    final TextInputLayout textLayout = Forms.newTextLayout(ctxt, false, false, 1f);
    EditText editText = Forms.newEditText(ctxt);
    editText.setText(text);
    editText.setInputType(InputType.TYPE_NULL);
    textLayout.addView(editText);

    Button button = Forms.newButton(ctxt, R.string.del_item_button, false, false, 0f);
    button.setTextAppearance(R.style.TextAppearance_AppCompat_Widget_Button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Views.removeView(linearLayout);
        if(ref != null) {
          ref.remove(linearLayout);
        }
      }
    });

    linearLayout.addView(textLayout);
    linearLayout.addView(button);
    return linearLayout;
  }


  @RequiresApi(api = Build.VERSION_CODES.M)
  public static LinearLayout newEditableRow(Context ctxt, String text, int inputType, final Map ref) {
    final LinearLayout linearLayout = Forms.newLinear(ctxt);
    linearLayout.setOrientation(LinearLayout.HORIZONTAL);

    final TextInputLayout textLayout = Forms.newTextLayout(ctxt, false, false, 1f);
    EditText editText = Forms.newEditText(ctxt);
    editText.setText(text);
    editText.setInputType(inputType);
    textLayout.addView(editText);

    Button button = Forms.newButton(ctxt, R.string.del_item_button, false, false, 0f);
    button.setTextAppearance(R.style.TextAppearance_AppCompat_Widget_Button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Views.removeView(linearLayout);
        if(ref != null) {
          ref.remove(linearLayout);
        }
      }
    });

    linearLayout.addView(textLayout);
    linearLayout.addView(button);
    return linearLayout;
  }
}
