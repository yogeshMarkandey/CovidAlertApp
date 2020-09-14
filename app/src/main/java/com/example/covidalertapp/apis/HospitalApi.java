package com.example.covidalertapp.apis;

import com.example.covidalertapp.model.HospitalDataResponse;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HospitalApi {

    @GET("hospitals/beds")
    Call<HospitalDataResponse> getHospitalData();
}
