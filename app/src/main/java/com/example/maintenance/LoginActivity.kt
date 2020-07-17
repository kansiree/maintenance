package com.example.maintenance

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.replace
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.maintenance.data.api.ApiHelperImpl
import com.example.maintenance.data.api.RetrofitBuilder
import com.example.maintenance.data.database.DatabaseBuilder
import com.example.maintenance.data.database.DatabaseHelperImpl
import com.example.maintenance.data.model.RoomDBViewModel
import com.example.maintenance.utils.Status
import com.example.maintenance.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.aircraft_fragment.*

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: RoomDBViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupUI()
    }

    private fun setupUI() {
        progressBar.visibility = View.GONE

        btn_login.setOnClickListener {
            setupViewModel()
            setupObserver()
//            if (savedInstanceState == null){
////                supportFragmentManager.beginTransaction().
////                replace(R.id.layout_login,LoadData.newInstance()).
////                commitAllowingStateLoss()
////            }

            Log.d("Main 2", "click")
        }
    }

    private fun setupObserver(){
        viewModel.getMasterAircraft().observe(this,Observer {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let { names -> Log.i("Aircraft","name: $names") }
                    progressBar.visibility = View.GONE
                    var intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                Status.LOADING -> {
                    Log.i("  ", "LOADING")

                    progressBar.visibility = View.VISIBLE
                    //   recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    Log.i("Aircraft ", "ERROR")

                    //Handle Error
                    progressBar.visibility = View.GONE
                    //      Toast.makeText(AircraftFragment().context, it.message, Toast.LENGTH_LONG).show()
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