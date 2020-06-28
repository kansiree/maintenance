package com.example.maintenance.data.database

import com.example.maintenance.data.database.entity.MasterAircraft

class DatabaseHelperImpl(private val appDatabase: AppDatabase):DatabaseHelper {
    override suspend fun getMasterSystem(): List<MasterAircraft> = appDatabase.aircraftDao().getMasterSystem()

    override suspend fun insertMaster(masterAircrafts: List<MasterAircraft>) = appDatabase.aircraftDao().insertMasterAircraft(masterAircrafts)

}