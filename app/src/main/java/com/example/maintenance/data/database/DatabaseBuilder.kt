package com.example.maintenance.data.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var DATABASE_NAME: String = "MAINTENANCE"
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase? {
        if (INSTANCE == null) {
            synchronized(DatabaseBuilder::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()


}
