package com.example.spreadsheet.Utill;

import com.example.spreadsheet.Model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaces {
    @GET("ckkgipaq2tmh1")
    Call<List<DataModel>> getData();
}
