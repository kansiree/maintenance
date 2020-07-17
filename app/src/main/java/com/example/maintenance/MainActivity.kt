package com.example.maintenance

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

import androidx.navigation.ui.setupWithNavController
import com.example.maintenance.data.database.dao.MasterDao
import com.example.maintenance.data.database.DatabaseBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var db: DatabaseBuilder? = null
    private var masterDao: MasterDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        Log.d(
            "MainActivity",
            "onCreate: " + if (savedInstanceState == null) "Null" else "not Null"
        )
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

    }


}
