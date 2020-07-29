package com.example.maintenance.data.model

import androidx.room.ColumnInfo

data class Menu(val nameMenuEng: String,val nameMenuTH: String, val pathImage: String)

data class Master( val id: Int, val full_name: String?, val createDate: String?)