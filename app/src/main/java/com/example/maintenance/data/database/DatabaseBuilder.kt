package com.example.maintenance.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import java.io.*

object DatabaseBuilder {
    private var DATABASE_NAME: String = "MAINTENANCE"
    private var INSTANCE: AppDatabase? = null
    private var TAG: String = "DatabaseBuilder"

    fun getInstance(context: Context): AppDatabase? {

        if (INSTANCE == null) {
            initDatabase(context, DATABASE_NAME)
            synchronized(DatabaseBuilder::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        initDatabase(context, DATABASE_NAME)

        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    private fun initDatabase(context: Context, databaseName: String) {
        val dbPath = context.getDatabasePath(databaseName)
        Log.i(TAG,dbPath.absolutePath)
        Log.i(TAG,dbPath.exists().toString())
        if (dbPath.exists()) {
            return
        }
         dbPath.parentFile.mkdirs()
         if(dbPath.exists()){
             // directory exists or already created
//             val dest = File(dbPath.parentFile, databaseName)
             try {
                 // response is the data written to file
                 val readResult = FileInputStream(dbPath.parentFile).bufferedReader().use { it.readText() }
                 println("readResult=$readResult")
             } catch (e: Exception) {
                 // handle the exception
             }
         }
        Log.i(TAG,dbPath.exists().toString())

    }

    fun destroyInstance(){
        INSTANCE = null
    }
}
