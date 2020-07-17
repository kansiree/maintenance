package com.example.maintenance.data.api

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getMasterAircraft() = apiService.getAircraft()

}