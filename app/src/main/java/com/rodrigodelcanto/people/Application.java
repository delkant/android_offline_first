package com.rodrigodelcanto.people;

import android.content.Context;
import android.os.HandlerThread;
import android.support.multidex.MultiDexApplication;

import com.rodrigodelcanto.mobile.db.PersistenceManager;
import com.rodrigodelcanto.mobile.pojo.ServicePrincipal;

/**
 * Created by delkant
 */

public class Application extends MultiDexApplication {

  public static final String TAG = "DEMO_Rodrigo_Del_Canto_MOBILE";
  private static PersistenceManager persistence = null;
  private static Context appContext;
  private static ServicePrincipal principal;
  private HandlerThread handlerThread;

  //
  public synchronized static ServicePrincipal getPrincipal() {
    return principal;
  }

  /* static methods */


  public synchronized static Application getApp() {
    return (Application) appContext;
  }

  public static PersistenceManager getPersistence() {
    return persistence;
  }

  public synchronized static void attachPersistence(String host) {
    detachPersistence();
    String baseUrl = "http://" + host + ":5984";
    persistence = new PersistenceManager(appContext, baseUrl);
  }

  public synchronized static void detachPersistence() {
    if (persistence != null) {
      persistence.closeSession();
      persistence = null;
    }
  }

  // session
  public static void startSession(String host, final String token, final String password) {
    // persistence
    if (Application.getPersistence() == null) {
      Application.attachPersistence(host);
    }

    String dbName = Constants.DEMO_DB;
    Application.getPersistence().openSession(dbName, null,token, password);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    this.appContext = this;
  }

  public HandlerThread getHandlerThead() {
    if (handlerThread == null) {
      handlerThread = new HandlerThread("handlerThread", android.os.Process.THREAD_PRIORITY_BACKGROUND);
      handlerThread.start();
    }

    return handlerThread;
  }

}
