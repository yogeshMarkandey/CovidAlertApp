package com.example.covidalertapp.apis;

import com.example.covidalertapp.model.DataForSampleTested;
import com.example.covidalertapp.model.SampleTestingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SampleTestingApi {

    @GET("stats/testing/history")
    Call<DataForSampleTested> getSampleTestingData();
}
