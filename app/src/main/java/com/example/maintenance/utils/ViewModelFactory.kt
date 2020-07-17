package com.example.maintenance.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.maintenance.data.api.ApiHelper
import com.example.maintenance.data.database.AppDatabase
import com.example.maintenance.data.database.DatabaseHelper
import com.example.maintenance.data.model.RoomDBViewModel

class ViewModelFactory( private val dbHelper: DatabaseHelper,private val apiHelper: ApiHelper ) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper,dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}