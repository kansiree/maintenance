package com.example.maintenance.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.maintenance.data.database.entity.MasterAircraft

@Dao
interface MasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMasterAircraft(masterAircraft: List<MasterAircraft>)

    @Query("SELECT * from master_system ORDER BY id ASC")
    fun getMasterSystem() : List<MasterAircraft>

    @Query("DELETE FROM master_system")
    fun deleteMasterSystem()
}