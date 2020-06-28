package com.example.maintenance.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "master_aircraft")
data class MasterAircraft (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "full_name") val full_name: String)


@Entity(tableName = "master_system")
data class MasterSystem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "full_name") val full_name: String)


@Entity(tableName = "master_technical_order")
data class MasterTecOrder (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "full_name") val full_name: String)

