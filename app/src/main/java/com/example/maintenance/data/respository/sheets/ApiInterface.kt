package com.example.maintenance.data.respository.sheets

import com.example.maintenance.data.model.AircraftObject
import retrofit2.Call
import  com.example.maintenance.ConfigApplication
import com.example.maintenance.ConfigApplication.Companion.API_KEY
import retrofit2.http.GET

interface ApiInterface {
        // val keyApi: String = "?key"+ API_KEY
        @GET("13wR1YqV2J7NoC-C687rJPMKKRxvVGOw2zPj3mQJVNco/values/Class%20Data?key=AIzaSyCl8UIMPVBW01g8hd7Ek1Bm9rue4EXfZAk&majorDimension=COLUMNS")
        fun getAircraft(): Call<AircraftObject>

}