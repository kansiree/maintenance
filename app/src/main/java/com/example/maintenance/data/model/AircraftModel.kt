package com.example.maintenance.data.model

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

data class  AircraftObject(@SerializedName("range") var range:String,@SerializedName("majorDimension") var majorDimension:String, @SerializedName("values") var value:ArrayList<ArrayList<String>>)

data class AircraftModel(var aircraftType: String,
                         var system: String,
                         var pilotOne: String,
                         var pilotTwo: String,
                         var recorder: String)

internal object APIClient{
    lateinit var retrofit: Retrofit

    val client: Retrofit
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
//                .addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build()


            retrofit = Retrofit.Builder()
                .baseUrl("https://sheets.googleapis.com/v4/spreadsheets/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit
        }
}