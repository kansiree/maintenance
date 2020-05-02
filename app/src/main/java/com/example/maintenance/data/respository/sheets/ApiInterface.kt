package com.example.maintenance.data.respository.sheets

import com.example.maintenance.data.model.AircraftObject
import retrofit2.Call
import  com.example.maintenance.ConfigApplication
import com.example.maintenance.ConfigApplication.Companion.API_KEY
import com.google.api.services.sheets.v4.model.AppendDimensionRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
        @GET("13wR1YqV2J7NoC-C687rJPMKKRxvVGOw2zPj3mQJVNco/values/Class%20Data")
        fun getAircraft(@Query ("key") apiKey :String,@Query("majorDimension") dimension :String): Call<AircraftObject>

}