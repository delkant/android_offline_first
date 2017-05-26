package com.rodrigodelcanto.mobile.db;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.rodrigodelcanto.mobile.pojo.MapBasedObject;
import com.rodrigodelcanto.mobile.types.Classes;
import com.rodrigodelcanto.people.Application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by delkant
 */

public class Q {
  public final static String VIEW = "VIEW_";

  protected static Database database() {
    return Application.getPersistence().getDatabase();
  }

  protected static Document document(String id) {
    return database().getExistingDocument(id);
  }

  protected static View getView(String name) {
    return database().getView(VIEW + name);
  }

  public static <T extends MapBasedObject> List<T> list(Query query, Class<T> tClass) {
    try {
      QueryEnumerator queryRows = query.run();
      return toList(queryRows, tClass);
    } catch (CouchbaseLiteException e) {
      return  new ArrayList<>();
    }

  }

  public static <T extends MapBasedObject> List<T> toList(QueryEnumerator queryRows, Class<T> tClass) {
    List<T> result = new ArrayList<>();

      for (Iterator<QueryRow> it = queryRows; it.hasNext(); ) {
        QueryRow row = it.next();
        Document doc = row.getDocument();
        T o = Classes.newInstance(tClass, tClass);
        o.load(doc.getProperties());
        result.add(o);
      }
    Log.d(Application.TAG, tClass +" found " + result.size());

   return result;
  }
}
