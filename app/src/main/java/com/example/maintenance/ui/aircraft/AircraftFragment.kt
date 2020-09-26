package com.example.maintenance.ui.aircraft

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.maintenance.R
import com.example.maintenance.data.api.ApiHelperImpl
import com.example.maintenance.data.api.RetrofitBuilder
import com.example.maintenance.data.database.DatabaseBuilder
import com.example.maintenance.data.database.DatabaseHelperImpl
import com.example.maintenance.data.database.entity.MasterAircraft
import com.example.maintenance.data.model.Master
import com.example.maintenance.data.model.RoomDBViewModel
import com.example.maintenance.ui.custom.spinner.CustomDropdownAircraftFragment
import com.example.maintenance.utils.Status
import com.example.maintenance.utils.ViewModelFactory
import kotlinx.android.synthetic.main.aircraft_fragment.*

class AircraftFragment : Fragment() {

    private lateinit var viewModel: RoomDBViewModel
    private var listAircraft: ArrayList<MasterAircraft> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.aircraft_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObserver()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(
            "AircraftFragment ac",
            "onCreate: " + if (savedInstanceState == null) "Null" else "not Null"
        )
        
    }

    private fun setupObserver(){
        Log.i("Aircraft ", "setupObserver")
        viewModel.getMasterAircraft().observe(viewLifecycleOwner, Observer { it ->
            when(it.status){
                Status.SUCCESS -> {
                    Log.i("Aircraft ", "SUCCESS")

                    it.data?.let {
                            names ->
                        var masterList: MutableList<Master> = List(names.size) {Master(1,"","")}.toMutableList()

                        val iterator = names.iterator()

                        for ((index, item) in iterator.withIndex()) {
                            masterList[index] =  Master(item.id,item.full_name,item.createDate)
                            println("The element at $index is $item")
                        }
                        spinner_aircraft_sn.adapter = context?.let { CustomDropdownAircraftFragment(it, masterList) }
                        Log.i("Aircraft","name: $names ") }
                    progressBar_main.visibility = View.GONE

                }
                Status.LOADING -> {
                    Log.i("  ", "LOADING")

                    progressBar_main.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Log.i("Aircraft ", "ERROR")
                    //Handle Error
                    progressBar_main.visibility = View.GONE
              //      Toast.makeText(AircraftFragment().context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.getMasterSystem().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    Log.i("Aircraft ", "SUCCESS")

                    it.data?.let {
                            names ->
                        var masterList: MutableList<Master> = List(names.size) {Master(1,"","")}.toMutableList()

                        val iterator = names.iterator()

                        for ((index, item) in iterator.withIndex()) {
                            masterList[index] =  Master(item.id,item.full_name,item.createDate)
                            println("The element at $index is $item")
                        }
                        spinner_system.adapter = context?.let { CustomDropdownAircraftFragment(it, masterList ) }
                        Log.i("System","name: $names ") }
                    progressBar_main.visibility = View.GONE

                }
                Status.LOADING -> {
                    Log.i("  ", "LOADING")

                    progressBar_main.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Log.i("Aircraft ", "ERROR")
                    //Handle Error
                    progressBar_main.visibility = View.GONE
                   //      Toast.makeText(AircraftFragment().context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupViewModel(){
        requireContext().applicationContext
        viewModel = ViewModelProvider(this,
            ViewModelFactory(
                DatabaseHelperImpl(
                    DatabaseBuilder.getInstance(requireContext().applicationContext)!!
                ),
                ApiHelperImpl(RetrofitBuilder.apiService)

            )
            ).get(RoomDBViewModel::class.java)
    }
}
