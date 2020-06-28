package com.example.maintenance.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "maintenance_detail")
data  class Detail(@PrimaryKey(autoGenerate = true) val id: Int,
                            @ColumnInfo(name =  "aircraft_type") val aircraft_type : String,
                            @ColumnInfo(name =  "aircraft_sn") val aircraft_sn : String,
                            @ColumnInfo(name =  "system") val system : String,
                            @ColumnInfo(name =  "primary_pilot") val primary_pilot : String,
                            @ColumnInfo(name =  "secondary_pilot") val secondary_pilot : String,
                            @ColumnInfo(name =  "recorder") val recorder : String,
                            @ColumnInfo(name =  "trouble") val trouble : String,
                            @ColumnInfo(name =  "technical_order") val technical_order : String,
                            @ColumnInfo(name =  "trouble_shooting") val trouble_shooting : String,
                            @ColumnInfo(name =  "replace") val replace : String,
                            @ColumnInfo(name =  "device_name") val device_name : String,
                            @ColumnInfo(name =  "part_number") val part_number : String,
                            @ColumnInfo(name =  "serial_number__remove") val serial_number__remove : String,
                            @ColumnInfo(name =  "serial_number_install") val serial_number_install : String,
                            @ColumnInfo(name =  "remark") val remark : String
    )
