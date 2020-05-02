package com.example.maintenance.data.manager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "master_aircraft")
  class MasterAircraftData ( @PrimaryKey(autoGenerate = true) val id: Int,
                             @ColumnInfo(name = "full_name") val full_name: String,
                             @ColumnInfo(name = "created_date") val create_date: Date )


@Entity(tableName = "master_system")
class MasterSystemData ( @PrimaryKey(autoGenerate = true) val id: Int,
                           @ColumnInfo(name = "full_name") val full_name: String,
                           @ColumnInfo(name = "created_date") val create_date: Date )

@Entity(tableName = "master_technical_order")
class MasterTechnicalOrderData ( @PrimaryKey(autoGenerate = true) val id: Int,
                           @ColumnInfo(name = "full_name") val full_name: String,
                           @ColumnInfo(name = "created_date") val create_date: Date )