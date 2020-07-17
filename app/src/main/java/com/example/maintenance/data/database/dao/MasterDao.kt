package com.example.maintenance.data.database.dao

import androidx.room.*
import com.example.maintenance.data.database.entity.MasterAircraft

@Dao
interface MasterDao {
    @Insert
    suspend fun insertMasterAircraft(masterAircraft: List<MasterAircraft>)

    @Query("SELECT * from master_aircraft")
    suspend fun getMasterAircraft() : List<MasterAircraft>

    @Query("DELETE FROM master_aircraft")
    suspend fun delete()
}