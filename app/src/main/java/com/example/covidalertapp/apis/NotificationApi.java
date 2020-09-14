package com.example.covidalertapp.apis;

import com.example.covidalertapp.model.NotificationDetailsResponses;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationApi {

    @GET("notifications")
    Call<NotificationDetailsResponses> getNotificationResponse();
}
