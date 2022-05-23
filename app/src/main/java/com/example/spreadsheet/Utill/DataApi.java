package com.example.spreadsheet.Utill;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataApi {
    public static  final  String BASE_URL = "https://sheetdb.io/api/v1/";
    public  static Retrofit retrofit = null;

    public static Retrofit getDataApi() {

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }

}
