package com.example.maintenance.data.api

import com.example.maintenance.data.model.responseapi.MasterResponse

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getMasterSystem() = apiService.getAircraft()

}