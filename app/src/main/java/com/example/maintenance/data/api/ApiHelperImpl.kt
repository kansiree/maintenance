package com.example.maintenance.data.api

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getMasterAircraft() = apiService.getAircraft()
    override suspend fun getMasterSystem() = apiService.getMasterSystem()
    override suspend fun getMasterTechnicalOrder() = apiService.getMasterTechnicalOrder()

}