package com.example.maintenance.data.database

import com.example.maintenance.data.database.entity.MasterAircraft

class DatabaseHelperImpl(private val appDatabase: AppDatabase):DatabaseHelper {
    override suspend fun getMasterAircraft(): List<MasterAircraft> = appDatabase.aircraftDao().getMasterAircraft()

    override suspend fun insertMaster(masterAircrafts: List<MasterAircraft>) = appDatabase.aircraftDao().insertMasterAircraft(masterAircrafts)

    override suspend fun deleteMaster() = appDatabase.aircraftDao().delete()

}
