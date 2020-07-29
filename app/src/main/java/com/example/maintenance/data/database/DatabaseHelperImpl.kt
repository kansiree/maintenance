package com.example.maintenance.data.database

import com.example.maintenance.data.database.entity.MasterAircraft
import com.example.maintenance.data.database.entity.MasterSystem
import com.example.maintenance.data.database.entity.MasterTecOrder
import com.example.maintenance.data.model.Master

class DatabaseHelperImpl(private val appDatabase: AppDatabase):DatabaseHelper {
    override suspend fun getMasterAircraft(): List<MasterAircraft> = appDatabase.aircraftDao().getMasterAircraft()

    override suspend fun insertMaster(masterAircrafts: List<MasterAircraft>) = appDatabase.aircraftDao().insertMasterAircraft(masterAircrafts)

    override suspend fun deleteMaster() = appDatabase.aircraftDao().delete()

    override suspend fun getMasterSystem(): List<MasterSystem> =appDatabase.aircraftDao().getMasterSystem()

    override suspend fun insertMasterSystem(masterSystem: List<MasterSystem>) = appDatabase.aircraftDao().insertMasterSystem(masterSystem)

    override suspend fun deleteSystem() = appDatabase.aircraftDao().deleteSystem()

    override suspend fun getMasterTechnicalOrder(): List<MasterTecOrder> = appDatabase.aircraftDao().getMasterTechnicalOrder()

    override suspend fun insertTechnicalOrder(masterTechnicalOrder: List<MasterTecOrder>) = appDatabase.aircraftDao().insertMasterTechnicalOrder(masterTechnicalOrder)

    override suspend fun deleteTechnicalOrder() = appDatabase.aircraftDao().deleteTechnicalOrder()

}
