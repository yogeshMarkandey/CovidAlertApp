package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contacts {

    @SerializedName("regional")
    List<ContactDetails> regional;

    public List<ContactDetails> getRegional() {
        return regional;
    }
}
