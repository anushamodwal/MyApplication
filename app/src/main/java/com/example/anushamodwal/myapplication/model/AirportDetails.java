package com.example.anushamodwal.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirportDetails{
    @SerializedName("@type")
    @Expose
    private String typeAD;

    @SerializedName("IATACode")
    @Expose
    private String codeIATA;

    public String getTypeAD() {
        return typeAD;
    }

    public void setTypeAD(String typeAD) {
        this.typeAD = typeAD;
    }

    public String getCodeIATA() {
        return codeIATA;
    }

    public void setCodeIATA(String codeIATA) {
        this.codeIATA = codeIATA;
    }

    @Override
    public String toString() {
        return "AirportDetails{" +
                "typeAD='" + typeAD + '\'' +
                ", codeIATA='" + codeIATA + '\'' +
                '}';
    }
}
