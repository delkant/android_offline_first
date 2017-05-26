package com.rodrigodelcanto.mobile.auth;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by delkant on 8/24/16.
 * https://github.com/colinskow/superlogin#main-api
 */

public class AuthUser {

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("group")
    private String group;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("currentPassword")
    private String currentPassword;
    @SerializedName("newPassword")
    private String newPassword;
    @SerializedName("confirmPassword")
    private String confirmPassword;
    @SerializedName("type")
    private String type = "user";
    @SerializedName("groups")
    private List<String> groups = new ArrayList<String>();
    @SerializedName("cellphone")
    private String cellphone;
    @SerializedName("roles")
    List<String> roles = new ArrayList<String>();

    private AuthUser() {

    }

    //SignUP constructor
    public static AuthUser signUpUserInstance(String fullname, String username, String email, String password, String confirmPassword, String groupName, String cellphone) {
        AuthUser authUser = new AuthUser();
        authUser.name = fullname;
        authUser.username = username;
        authUser.email = email;
        authUser.password = password;
        authUser.confirmPassword = confirmPassword;
        authUser.group = groupName;
        authUser.groups.add(groupName);
        authUser.cellphone = cellphone;
        return authUser;
    }

    //Login/SignIn constructor
    public static AuthUser signInUserInstance(String username, String password, String group) {
        AuthUser authUser = new AuthUser();
        authUser.username = username;
        authUser.password = password;
        authUser.group = group;
        return authUser;
    }

    //Used for Password changes
    public static AuthUser changePassInstance(String currentPassword, String confirmPassword) {
        AuthUser authUser = new AuthUser();
        authUser.currentPassword = currentPassword;
        authUser.newPassword = confirmPassword;
        authUser.confirmPassword = confirmPassword;
        return authUser;
    }

    public Map<String, String> getLoginData() {
        Map<String, String> data = new HashMap<>();
        data.put("username", this.username);
        data.put("password", this.password);
        return data;
    }
}
