package com.example.maintenance.data.api

import com.example.maintenance.data.model.responseapi.MasterResponse

interface ApiHelper {
    suspend fun getMasterAircraft():MasterResponse

    suspend fun getMasterSystem():MasterResponse

    suspend fun getMasterTechnicalOrder():MasterResponse

}