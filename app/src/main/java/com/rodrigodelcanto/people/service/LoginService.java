package com.rodrigodelcanto.people.service;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Callback;

/**
 * Created by delkant
 */

public class LoginService {

  public static void login(String host, String username, String password, Callback<ServiceResponse> cb) {
    final String method = "mobile/login/Login";


    Map message = new HashMap();

  }

  public static void logout(Callback<ServiceResponse> cb) {
    final String method = "mobile/login/Logout";

    Map message = new HashMap();

  }

}
