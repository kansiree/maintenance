package com.example.maintenance.data.model.responseapi

import com.google.gson.annotations.SerializedName

data class MasterResponse (
    @SerializedName("error")
    val error: Int = 0,
    @SerializedName("message")
    val message: ArrayList<Master>
)

data class Master(   @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("full_name")
                     val fullName:String = "",
                     @SerializedName("created_date")
                     val createDate:String = ""
)