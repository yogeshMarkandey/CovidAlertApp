package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class NotificationObject {

    @SerializedName("title")
    private String title ;
    @SerializedName("link")
    private String link ;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
