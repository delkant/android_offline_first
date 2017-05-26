package com.rodrigodelcanto.mobile.auth;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by delkant on 4/11/17.
 */

public class API {

  public static void login(AuthAPI authService, final String group, final String username, final String password, Callback<Session> callback) {
    AuthUser authUser = AuthUser.signInUserInstance(username, password, group);
    Call<Session> mService = authService.login(authUser);
    mService.enqueue(callback);
  }

  public static void Logout(AuthAPI authService, final Session session, Callback<Session> callback) {
    if (session != null) { //guess user doesn't have a session.
      //perform remote localLogout
      Call<Session> mService = authService.logout("Bearer " + session.getToken() + ":" + session.getPassword());
      mService.enqueue(callback);
    }
  }
}
