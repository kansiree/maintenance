package com.example.maintenance.data.respository.sheets

import com.example.maintenance.data.model.AircraftObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("13wR1YqV2J7NoC-C687rJPMKKRxvVGOw2zPj3mQJVNco/values/Class%20Data?key=AIzaSyCl8UIMPVBW01g8hd7Ek1Bm9rue4EXfZAk")
        fun getAircraft(): Call<AircraftObject>

}