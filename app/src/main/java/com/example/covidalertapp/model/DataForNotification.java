package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataForNotification {

    @SerializedName("notifications")
    private List<NotificationObject> notifications;

    public List<NotificationObject> getNotifications() {
        return notifications;
    }
}
