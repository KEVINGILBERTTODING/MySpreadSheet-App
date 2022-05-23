package com.example.spreadsheet.Model;

import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("nama")
    private String nama;
    @SerializedName("email")
    private String email;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("timestamp")
    private String timestamp;


    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
