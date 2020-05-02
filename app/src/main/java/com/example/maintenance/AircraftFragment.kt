package com.example.maintenance

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.maintenance.data.model.APIClient
import com.example.maintenance.data.model.AircraftObject
import com.example.maintenance.data.respository.sheets.ApiInterface
import kotlinx.android.synthetic.main.aircraft_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AircraftFragment : Fragment() {

    companion object {
        fun newInstance() = AircraftFragment()
    }

    private lateinit var viewModel: AircraftViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.aircraft_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var list: List<String>  = arrayListOf("selected", "1", "2")

        setUpSpinner(spinner_aircraft_sn,list);
        setUpSpinner(spinner_system,list)

        spinner_aircraft_sn.onItemSelectedListener =  object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(context, list[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
//                Toast.makeText(context, itemAircraft.aircraftItem.toString(), Toast.LENGTH_SHORT).show()
                /* write code to perform some action*/ }
        }

        var apiInterface = APIClient.client.create(ApiInterface::class.java)

        val call = apiInterface.getAircraft(ConfigApplication.API_KEY,"COLUMNS")
        call.enqueue(object : Callback<AircraftObject> {
            override fun onResponse(call: Call<AircraftObject>, response: Response<AircraftObject>) {
                Log.d("Success!", response.toString())
                var text = response.body()
                Log.d("Success text!", text.toString())
            }

            override fun onFailure(call: Call<AircraftObject>, t: Throwable)                  {
                Log.e("Failed Query :(", t.toString())

            }
        })

    }

    private  fun setUpSpinner(view: Spinner,listData:List<String>){
        view.adapter = ArrayAdapter(this!!.requireContext(), R.layout.support_simple_spinner_dropdown_item, listData)
    }

    private fun getItemSelect(view: Spinner):String{
        return ""
    }

}
