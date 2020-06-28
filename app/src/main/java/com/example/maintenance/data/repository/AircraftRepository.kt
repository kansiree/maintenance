package com.example.maintenance.data.repository

import android.app.Application
import com.example.maintenance.data.database.dao.MasterDao
import com.example.maintenance.data.database.DatabaseBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class AircraftRepository(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var masterDao: MasterDao?

    init {
        val db = DatabaseBuilder.getInstance(application)
        masterDao = db?.aircraftDao()
    }


}