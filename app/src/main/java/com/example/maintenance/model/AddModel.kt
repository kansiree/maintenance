package com.example.maintenance.model

import com.google.gson.annotations.SerializedName

data class AddModel (
    @SerializedName("dataName") val dataName: String,
    @SerializedName("conutry") val conutry :String
)
