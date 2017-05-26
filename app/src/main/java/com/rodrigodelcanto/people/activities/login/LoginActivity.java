package com.rodrigodelcanto.people.activities.login;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rodrigodelcanto.mobile.commons.adapter.EntryData;
import com.rodrigodelcanto.mobile.commons.adapter.RadioAdapter;
import com.rodrigodelcanto.mobile.commons.view.ExpandableHeightListView;
import com.rodrigodelcanto.mobile.pojo.ServicePrincipal;
import com.rodrigodelcanto.mobile.types.Strings;
import com.rodrigodelcanto.mobile.utils.Activities;
import com.rodrigodelcanto.mobile.utils.Progressmeters;
import com.rodrigodelcanto.mobile.utils.Toasts;
import com.rodrigodelcanto.mobile.utils.Views;
import com.rodrigodelcanto.people.Application;
import com.rodrigodelcanto.people.Constants;
import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.DemoException;
import com.rodrigodelcanto.people.activities.person.PeopleMainActivity;
import com.rodrigodelcanto.people.service.ServiceResponse;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  // UI references.
  private EditText usernameView;
  private EditText passwordView;
  private View progressView;
  private View loginFormContainer;
  private RadioAdapter radioAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.lgn_login);

    // Set up the login form.
    usernameView = (EditText) findViewById(R.id.username);
    passwordView = (EditText) findViewById(R.id.password);
    passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == R.id.login_action || id == EditorInfo.IME_NULL) {
          attemptLogin();
          return true;
        }
        return false;
      }
    });

    // radio adapter
    radioAdapter = new RadioAdapter(this, getHosts());
    prepareRadioContainer(radioAdapter);

    Button loginButton = (Button) findViewById(R.id.login_button);
    loginButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        attemptLogin();
      }
    });

    loginFormContainer = findViewById(R.id.login_form_container);
    progressView = findViewById(R.id.login_progress);
  }

  private List<EntryData> getHosts() {
    List<EntryData> list = new LinkedList<>();
    list.add(new EntryData("192.168.1.100", "AWS (Self Hosted)", false));
    list.add(new EntryData("192.168.0.14", "Cloudant Couch AS", false));
    return list;
  }

  private void prepareRadioContainer(RadioAdapter adapter) {
    ExpandableHeightListView view = (ExpandableHeightListView) findViewById(R.id.login_host_list);
    view.setAdapter(adapter);
    view.setExpanded(true);
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        InputMethodManager imm = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return false;
      }
    });
    view.setSelection(0);
  }

  private void attemptLogin() {
    try {
      validateForm();

      String host = radioAdapter.getSelectedId();
      String username = usernameView.getText().toString();
      String password = passwordView.getText().toString();

      Application.startSession(Constants.DEMO_HOST, Constants.DEMO_TOKEN, Constants.DEMO_PASS);
      Activities.replace(LoginActivity.this, PeopleMainActivity.class);
    } catch (DemoException ex) {
      Views.setError(ex.getView(), ex.getMessage());
    }
  }

  private void validateForm() throws DemoException {
    // clean errors
    usernameView.setError(null);
    passwordView.setError(null);

    String username = usernameView.getText().toString();
    if (Strings.isEmpty(username)) {
      throw new DemoException(getString(R.string.err_field_required), usernameView);
    }

    String password = passwordView.getText().toString();
    if (Strings.isEmpty(password)) {
      throw new DemoException(getString(R.string.err_field_required), passwordView);
    }
  }

  private Callback<ServiceResponse> newLoginCallback(final String host, final String username) {
    return new Callback<ServiceResponse>() {
      @Override
      public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> httpResponse) {
        ServiceResponse response = httpResponse.body();

        showProgress(false);

        if (httpResponse.code() == 200 && response.isOk()) {

          // set principal and worker
          ServicePrincipal principal = ServicePrincipal.wrap(response.getMessage().get("user"));
          Application.startSession(Constants.DEMO_HOST, Constants.DEMO_TOKEN, Constants.DEMO_PASS);

          Activities.replace(LoginActivity.this, PeopleMainActivity.class);

        } else {
          Views.setError(passwordView, getString(R.string.lgn_err_check_password));
          Toasts.showShort(LoginActivity.this, R.string.lgn_err_access_denied);
        }
      }

      @Override
      public void onFailure(Call<ServiceResponse> call, Throwable t) {
        call.cancel();
        showProgress(false);
        Toasts.showLong(LoginActivity.this, "Red no disponible");
      }
    };
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  private void showProgress(final boolean visible) {
    int animTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
    Progressmeters.toggleVisibility(progressView, loginFormContainer, visible, animTime);
  }

}

