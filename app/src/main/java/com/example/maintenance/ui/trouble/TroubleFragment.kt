package com.example.maintenance.ui.trouble

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.maintenance.R
import com.example.maintenance.data.api.ApiHelperImpl
import com.example.maintenance.data.api.RetrofitBuilder
import com.example.maintenance.data.database.DatabaseBuilder
import com.example.maintenance.data.database.DatabaseHelperImpl
import com.example.maintenance.data.model.Master
import com.example.maintenance.data.model.RoomDBViewModel
import com.example.maintenance.ui.custom.spinner.CustomDropdownAircraftFragment
import com.example.maintenance.ui.custom.spinner.CustomDropdownTroubleFragment
import com.example.maintenance.utils.Status
import com.example.maintenance.utils.ViewModelFactory
import kotlinx.android.synthetic.main.aircraft_fragment.*
import kotlinx.android.synthetic.main.custom_spinner_trouble_fragment.*
import kotlinx.android.synthetic.main.trouble_fragment.*


class TroubleFragment : Fragment() {

    companion object {
        fun newInstance() = TroubleFragment()
    }

    private lateinit var viewModel: RoomDBViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trouble_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        Log.i("Trouble ", "setupObserver")
        viewModel.getMasterTechnicalOrder().observe(viewLifecycleOwner, Observer { it ->
            when(it.status){
                Status.SUCCESS -> {
                    Log.i("Trouble ", "SUCCESS")

                    it.data?.let {
                            names ->
                        var masterList: MutableList<Master> = List(names.size) { Master(1,"","") }.toMutableList()

                        val iterator = names.iterator()

                        for ((index, item) in iterator.withIndex()) {
                            masterList[index] =  Master(item.id,item.full_name,item.createDate)
                            println("The element at $index is $item")
                        }
                        spinner_technical_order.adapter = context?.let { CustomDropdownTroubleFragment(it, masterList) }
                        Log.i("Trouble","name: $names ") }
                    progressBar_trouble.visibility = View.GONE

                }
                Status.LOADING -> {
                    Log.i("Trouble", "LOADING")

                    progressBar_trouble.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Log.i("Trouble ", "ERROR")
                    //Handle Error
                    progressBar_trouble.visibility = View.GONE
                    //      Toast.makeText(AircraftFragment().context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })    }

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
