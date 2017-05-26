package com.rodrigodelcanto.mobile.auth;

/**
 * Created by delkant on 8/24/16.
 */

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthAPI {
    @POST("auth/login")
    Call<Session> login(@Body AuthUser authUser);

    @POST("auth/register")
    Call<Session> register(@Body AuthUser authUser);

    @POST("auth/logout")
    Call<Session> logout(@Header("Authorization") String authorization);

    @POST("auth/password-change")
    Call<Session> passwordChange(@Header("Authorization") String authorization, @Body AuthUser authUser);

}