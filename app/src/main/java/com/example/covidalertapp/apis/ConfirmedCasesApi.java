package com.example.covidalertapp.apis;

import com.example.covidalertapp.model.ConfirmedCasesResponse;
import com.example.covidalertapp.model.DayWiseConfirmedCases;
import com.example.covidalertapp.model.MedicalCollegesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConfirmedCasesApi {

    @GET("stats/history")
    Call<ConfirmedCasesResponse> getConfirmedCasesData();


}
