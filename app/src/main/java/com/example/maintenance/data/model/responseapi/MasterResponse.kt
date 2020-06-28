package com.example.maintenance.data.model.responseapi

import com.google.gson.annotations.SerializedName

class MasterResponse {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("full_name")
    var fullName:String? = null
    @SerializedName("create_date")
    var createDate:String? = null
}