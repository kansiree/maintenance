package com.example.maintenance.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.maintenance.data.database.dao.MasterDao
import com.example.maintenance.data.database.entity.*

@Database(
    entities = [MasterAircraft::class,
        MasterSystem::class,
        MasterTecOrder::class,
        Detail::class], version = 2, exportSchema = true
)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun aircraftDao(): MasterDao

}