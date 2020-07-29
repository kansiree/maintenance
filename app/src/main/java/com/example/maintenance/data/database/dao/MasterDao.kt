package com.example.maintenance.data.database.dao

import androidx.room.*
import com.example.maintenance.data.database.entity.MasterAircraft
import com.example.maintenance.data.database.entity.MasterSystem
import com.example.maintenance.data.database.entity.MasterTecOrder

@Dao
interface MasterDao {
    @Insert
    suspend fun insertMasterAircraft(masterAircraft: List<MasterAircraft>)

    @Query("SELECT * from master_aircraft")
    suspend fun getMasterAircraft() : List<MasterAircraft>

    @Query("DELETE FROM master_aircraft")
    suspend fun delete()

    @Insert
    suspend fun insertMasterSystem(masterSystem: List<MasterSystem>)

    @Query("SELECT * from master_system")
    suspend fun getMasterSystem() : List<MasterSystem>

    @Query("DELETE FROM master_system")
    suspend fun deleteSystem()

    @Insert
    suspend fun insertMasterTechnicalOrder(masterTecOrder : List<MasterTecOrder>)

    @Query("SELECT * from master_technical_order")
    suspend fun getMasterTechnicalOrder() : List<MasterTecOrder>

    @Query("DELETE FROM master_technical_order")
    suspend fun deleteTechnicalOrder()
}