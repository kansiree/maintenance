package com.example.maintenance.data.database

import com.example.maintenance.data.database.entity.MasterAircraft

interface DatabaseHelper {
    suspend fun getMasterSystem(): List<MasterAircraft>

    suspend fun insertMaster(masterAircrafts: List<MasterAircraft>)
}