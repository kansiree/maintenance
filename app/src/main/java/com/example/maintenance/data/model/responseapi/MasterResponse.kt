package com.example.maintenance.data.model.responseapi

import com.google.gson.annotations.SerializedName

data class MasterResponse (
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("full_name")
    val fullName:String = "",
    @SerializedName("create_date")
    val createDate:String = ""
)