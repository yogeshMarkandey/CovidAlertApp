package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class NotificationDetailsResponses {

    @SerializedName("data")
    private DataForNotification data;

    public DataForNotification getData() {
        return data;
    }
}
