package com.example.spreadsheet.Response;

import com.google.gson.annotations.SerializedName;

public class ResponseInsert {
    @SerializedName("status")
    private String status;

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "ResponseInsert{" +
                        "status = '" + status + '\'' +
                        "}";
    }
}
