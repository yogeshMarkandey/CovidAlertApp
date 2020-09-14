package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class ContactDetailResponses {

    @SerializedName("data")
    private DataContact data;

    public DataContact getData() {
        return data;
    }
}
