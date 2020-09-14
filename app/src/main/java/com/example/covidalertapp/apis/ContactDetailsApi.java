package com.example.covidalertapp.apis;

import com.example.covidalertapp.model.ContactDetailResponses;
import com.example.covidalertapp.model.ContactDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactDetailsApi {

    @GET ("contacts")
    Call<ContactDetailResponses> getContactDetails();
}
