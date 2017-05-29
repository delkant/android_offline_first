package com.rodrigodelcanto.mobile.db;

import android.content.Context;
import android.os.Handler;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseOptions;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.auth.Authenticator;
import com.couchbase.lite.auth.AuthenticatorFactory;
import com.couchbase.lite.replicator.RemoteRequestResponseException;
import com.couchbase.lite.replicator.Replication;
import com.rodrigodelcanto.mobile.utils.Toasts;
import com.couchbase.lite.util.Log;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author delkant
 */
public class PersistenceManager implements Replication.ChangeListener {
  // Storage Type: .SQLITE_STORAGE or .FORESTDB_STORAGE
  private static final String STORAGE_TYPE = Manager.SQLITE_STORAGE;
  // Encryption (Don't store encryption key in the source code. We are doing it here just as an example):
  private static final boolean ENCRYPTION_ENABLED = false;
  private static final String ENCRYPTION_KEY = "seekrit";
  // Logging:
  private static final boolean LOGGING_ENABLED = false;
  // Guest database:
  public static String TAG = "COUCH_SYNC";

  protected String remoteBaseUrl;
  private Manager manager;
  private String localDbName;
  private Database localDb;
  private String remoteDbName;
  private Replication repPull;
  private Replication repPush;
  private Throwable repError;
  private Context context;
  // access data
  private String principal;
  private String token;
  private String password;

  public PersistenceManager(Context context, String remoteBaseUrl) {
    this.context = context;
    this.remoteBaseUrl = remoteBaseUrl;
    checkConfig();
    enableLogging();
    //SharedPreferences  prefs = new Preferences(context, context.getSharedPreferences("com.mburukuja.soa.wrench", Context.MODE_PRIVATE));
  }

  private Manager getManager() {
    if (manager == null) {
      try {
        AndroidContext androidContext = new AndroidContext(this.context.getApplicationContext());
        manager = new Manager(androidContext, Manager.DEFAULT_OPTIONS);

      } catch (Exception e) {
        Log.e(TAG, "Cannot newInstance Manager object", e);
      }
    }

    return manager;
  }

  public Database getDatabase() {
    if (localDb == null) {
      localDb = getLocalDatabase(localDbName);
    }
    return localDb;
  }

  public void openSession(String dbName, String principal, String token, String password) {
    closeSession();

    this.localDbName = dbName;
    this.principal = principal;
    this.token = token;
    this.password = password;
    getDatabase();
    Authenticator auth = AuthenticatorFactory.createBasicAuthenticator(token, password);
    startReplication(auth);
  }

  public void openSession(String dbName, String principal) {
    closeSession();

    this.localDbName = dbName;
    this.principal = principal;
    getDatabase();
    startReplication();
  }

  public void closeSession() {
    localDbName = null;
    principal = null;
    token = null;
    password = null;
    stopReplication();
    localDb = null;
  }

  public boolean isSessionActive() {
    return localDb != null;
  }

  public String getPrincipal() {
    return principal;
  }


  /**
   * Replicator
   */

  private URL getSyncUrl() {
    URL url = null;
    try {
      url = new URL(remoteBaseUrl + "/" + remoteDbName);
    } catch (MalformedURLException e) {
      Log.e(TAG, "Invalid sync url", e);
    }
    return url;
  }

  private void startReplication() {
    startReplication(null);
  }

  private void startReplication(Authenticator auth) {
    android.util.Log.d(TAG, "=====================================>>>>>>>>>>>>>>>> START REPLICATION");
    if (repPull == null) {
      repPull = getDatabase().createPullReplication(getSyncUrl());
      repPull.setContinuous(true);
      if(auth != null) {
        repPull.setAuthenticator(auth);
      }
      repPull.addChangeListener(this);
    }

    if (repPush == null) {
      repPush = getDatabase().createPushReplication(getSyncUrl());
      repPush.setContinuous(true);
      if(auth != null) {
        repPush.setAuthenticator(auth);
      }
      repPush.addChangeListener(this);
    }

    repPull.stop();
    repPull.start();

    repPush.stop();
    repPush.start();
  }

  private void stopReplication() {
    android.util.Log.d(TAG, "=====================================>>>>>>>>>>>>>>>> STOP REPLICATION");
    if (repPull != null) {
      repPull.removeChangeListener(this);
      repPull.stop();
      repPull = null;
    }

    if (repPush != null) {
      repPush.removeChangeListener(this);
      repPush.stop();
      repPush = null;
    }
  }

  @Override
  public void changed(Replication.ChangeEvent event) {
    Throwable error = null;
    if (repPull != null) {
      if (error == null)
        error = repPull.getLastError();
    }

    if (error == null || error == repError)
      error = repPush.getLastError();

    if (repError != null && error != repError) {
      repError = error;
      showErrorMessage(repError.getMessage(), null);
    }
    if (error instanceof RemoteRequestResponseException) {
      RemoteRequestResponseException exception = (RemoteRequestResponseException) error;
      if (exception.getCode() == 401) {
        // Authentication error: unauthorized
        //XXX remoteAndLocalLogout();
      }
    }
  }


  /**
   * Display error message
   */

  public void showErrorMessage(final String errorMessage, final Throwable throwable) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        android.util.Log.e(TAG, errorMessage, throwable);
        String msg = String.format("%s: %s", errorMessage, throwable != null ? throwable : "");
        Toasts.showShort(context.getApplicationContext(), msg);
      }
    });
  }

  private void runOnUiThread(Runnable runnable) {
    Handler mainHandler = new Handler(context.getApplicationContext().getMainLooper());
    mainHandler.post(runnable);
  }

  // privates

  private Database getLocalDatabase(String dbName) {
    Database localDB = null;
    try {
      remoteDbName = dbName; //???
      DatabaseOptions options = new DatabaseOptions();
      options.setCreate(true);
      options.setStorageType(STORAGE_TYPE);
      options.setEncryptionKey(ENCRYPTION_ENABLED ? ENCRYPTION_KEY : null);
      localDB = getManager().getExistingDatabase(dbName);
      Log.d(TAG, "DB_Dir(" + dbName + ") :" + context.getFilesDir());
      if (localDB == null) {
//                try {
//                   TODO: save default values?? ZipUtils.unzip(getAssets().open("catalog.zip"), manager.getContext().getFilesDir());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                localDB = getManager().getExistingDatabase(dbName);
        Log.d(TAG, "ATTENTION! =========> DATABASE (" + dbName + ") DOESN'T EXISTS. CREATING DB" + dbName);
        localDB = getManager().openDatabase(dbName, options);
      }

    } catch (CouchbaseLiteException e) {
      Log.e(TAG, "Cannot newInstance database for name: " + dbName, e);
    }
    return localDB;
  }


  private void checkConfig() {
    if (remoteBaseUrl == null) {
      Log.e(TAG, "remoteBaseUrl Cannot be null");
    }
  }

  private void enableLogging() {
    if (LOGGING_ENABLED) {
      Manager.enableLogging(Log.TAG, Log.VERBOSE);
      Manager.enableLogging(Log.TAG_SYNC_ASYNC_TASK, Log.VERBOSE);
      Manager.enableLogging(Log.TAG_SYNC, Log.VERBOSE);
      Manager.enableLogging(Log.TAG_QUERY, Log.VERBOSE);
      Manager.enableLogging(Log.TAG_VIEW, Log.VERBOSE);
      Manager.enableLogging(Log.TAG_DATABASE, Log.VERBOSE);
    }
    Manager.enableLogging(TAG, Log.VERBOSE);
  }
}
