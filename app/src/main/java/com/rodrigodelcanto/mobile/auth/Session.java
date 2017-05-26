package com.rodrigodelcanto.mobile.auth;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by delkant on 8/24/16.
 *  https://github.com/colinskow/superlogin#quick-start
 * {
 *     "issued": 1440232999594,
 *     "expires": 1440319399594,
 *     "provider": "local",
 *     "ip": "127.0.0.1",
 *     "token": "aViSVnaDRFKFfdepdXtiEg",
 *     "password": "p7l9VCNbTbOVeuvEBhYW_A",
 *     "user_id": "joesmith",
 *     "roles": [
 *       "user"
 *     ],
 *     "userDBs": {
 *         "supertest": "http://aViSVnaDRFKFfdepdXtiEg:p7l9VCNbTbOVeuvEBhYW_A@localhost:5984/supertest$joesmith"
 *     }
 *  }
 * */

public class Session {

    @SerializedName("issued")   private String issued;
    @SerializedName("expires")  private String expires;
    @SerializedName("provider") private String provider;
    @SerializedName("ip")       private String ip;
    @SerializedName("token")    private String token;
    @SerializedName("password") private String password;
    @SerializedName("user_id")  private String userId;
    @SerializedName("roles")    private List<String> roles = new ArrayList<String>();
    @SerializedName("success")  private boolean success = false;

    public Session(String userId, String issued, String expires, String provider, String ip, String token, String password) {
        this.userId = userId;
        this.issued = issued;
        this.expires = expires;
        this.provider = provider;
        this.ip = ip;
        this.token = token;
        this.password = password;
    }

    boolean wasSuccess(){
        return success;
    }

    boolean isRegistered(){
        return success;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public String getProvider() {
        return provider;
    }

    public String getExpires() {
        return expires;
    }

    public String getIp() {
        return ip;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getIssued() {
        return issued;
    }
}