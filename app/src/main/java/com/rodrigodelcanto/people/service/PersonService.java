package com.rodrigodelcanto.people.service;


import com.rodrigodelcanto.mobile.types.Maps;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Callback;

/**
 * Created by delkant
 */

public class PersonService {

  public static void searchPerson(String docnum, Callback<ServiceResponse> cb) {
    final String method = "mobile/person/SearchPerson";

    Map message = new HashMap();
    Maps.insert(message, "document.value", docnum);

  }

}
