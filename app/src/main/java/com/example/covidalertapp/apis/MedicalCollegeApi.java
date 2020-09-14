package com.example.covidalertapp.apis;

import com.example.covidalertapp.model.MedicalCollegesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MedicalCollegeApi {

    @GET("hospitals/medical-colleges")
    Call<MedicalCollegesResponse> getCollegeData();
}
