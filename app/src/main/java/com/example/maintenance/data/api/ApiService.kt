package com.example.maintenance.data.api

import com.example.maintenance.data.model.responseapi.MasterResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("getMasterSystem")
    fun getAircraft(): List<MasterResponse>
}