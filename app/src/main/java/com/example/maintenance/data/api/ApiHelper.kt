package com.example.maintenance.data.api

import com.example.maintenance.data.model.responseapi.MasterResponse

interface ApiHelper {
    suspend fun getMasterAircraft():List<MasterResponse>

    suspend fun getMasterSystem():List<MasterResponse>

    suspend fun getMasterTechnicalOrder():List<MasterResponse>

}