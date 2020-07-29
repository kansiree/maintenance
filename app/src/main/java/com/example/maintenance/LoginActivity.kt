package com.example.maintenance

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.maintenance.data.api.ApiHelperImpl
import com.example.maintenance.data.api.RetrofitBuilder
import com.example.maintenance.data.database.DatabaseBuilder
import com.example.maintenance.data.database.DatabaseHelperImpl
import com.example.maintenance.data.model.RoomDBViewModel
import com.example.maintenance.utils.Status
import com.example.maintenance.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: RoomDBViewModel
    var TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseBuilder.destroyInstance()
    }

    private fun setupUI() {
        progressBar.visibility = View.GONE
        btn_login.setOnClickListener {
            setupViewModel()
            setupObserver()
        }
    }

    private fun setupObserver(){
        viewModel.getStatusInsertMaster().observe(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    var intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this,
            ViewModelFactory(
                DatabaseHelperImpl(
                    DatabaseBuilder.getInstance(applicationContext)!!
                ),
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(RoomDBViewModel::class.java)
    }
}