package com.example.maintenance.data.database

import com.example.maintenance.data.database.entity.MasterAircraft
import com.example.maintenance.data.database.entity.MasterSystem
import com.example.maintenance.data.database.entity.MasterTecOrder
import com.example.maintenance.data.model.Master

interface DatabaseHelper {
    suspend fun getMasterAircraft(): List<MasterAircraft>

    suspend fun insertMaster(masterAircrafts: List<MasterAircraft>)

    suspend fun deleteMaster()

    suspend fun getMasterSystem(): List<MasterSystem>

    suspend fun insertMasterSystem(masterSystem: List<MasterSystem>)

    suspend fun deleteSystem()

    suspend fun getMasterTechnicalOrder(): List<MasterTecOrder>

    suspend fun insertTechnicalOrder(masterTechnicalOrder: List<MasterTecOrder>)

    suspend fun deleteTechnicalOrder()
}