package com.rodrigodelcanto.mobile.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import com.rodrigodelcanto.people.R;

import java.util.Map;

/**
 * Created by delkant
 */

public class Dialogs {

  public static AlertDialog newSelector(Context ctxt,
                                        int title,
                                        final String[] items,
                                        DialogInterface.OnClickListener listener) {

    AlertDialog.Builder builder = new AlertDialog.Builder(ctxt);
    builder.setTitle(title);
    builder.setItems(items, listener);

    return builder.create();
  }

  public static AlertDialog newOkCancel(Context ctxt,
                                        int title,
                                        int message,
                                        DialogInterface.OnClickListener listener) {

    AlertDialog.Builder builder = new AlertDialog.Builder(ctxt);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.setPositiveButton(R.string.dialog_ok_button, listener);

    return builder.create();
  }

  public static void prompt(Context ctxt,
                            int title,
                            int message,
                            int inputType,
                            final Map returnedObj) {

    AlertDialog.Builder builder = new AlertDialog.Builder(ctxt);
    builder.setTitle(title);
    builder.setMessage(message);

    final EditText inputView = new EditText(ctxt);
    inputView.setInputType(inputType);
    builder.setView(inputView);

    builder.setPositiveButton(R.string.dialog_ok_button,
      new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int wich) {
          String input = inputView.getText().toString();
          if (returnedObj != null) {
            returnedObj.put("result", input);
          }
        }
      });
    builder.setNegativeButton(R.string.dialog_cancel_button,
      new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int wich) {
          dialog.cancel();
        }
      });

    // is blocking??
    builder.show();
  }

}
