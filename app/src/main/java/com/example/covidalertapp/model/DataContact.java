package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class DataContact {
    @SerializedName("contacts")
    private Contacts contacts;

    public Contacts getContacts() {
        return contacts;
    }
}
