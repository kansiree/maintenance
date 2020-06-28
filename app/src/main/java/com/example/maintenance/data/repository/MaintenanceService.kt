package com.example.maintenance.data.repository

import retrofit2.Call
import com.example.maintenance.data.model.responseapi.MasterResponse
import retrofit2.http.GET

interface MaintenanceService {
        @GET("getMasterSystem")
        fun getAircraft(): Call<List<MasterResponse>>

}