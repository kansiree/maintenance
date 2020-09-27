package com.example.maintenance.data.api

import com.example.maintenance.data.model.responseapi.MasterResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("getMasterAircraft")
    suspend fun getAircraft(): MasterResponse

    @GET("getMasterSystem")
    suspend fun getMasterSystem(): MasterResponse

    @GET("getMasterTechnicalOrder")
    suspend fun getMasterTechnicalOrder(): MasterResponse
}