package com.example.maintenance.data.database

import com.example.maintenance.data.database.entity.MasterAircraft

interface DatabaseHelper {
     suspend fun getMasterAircraft(): List<MasterAircraft>

    suspend fun insertMaster(masterAircrafts: List<MasterAircraft>)

    suspend fun deleteMaster()
}