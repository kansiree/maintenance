package com.example.maintenance.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maintenance.R
import com.example.maintenance.data.model.Menu
import com.example.maintenance.ui.adapter.MenuAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(){

    private lateinit var homeViewModel: HomeViewModel
//    private lateinit var editAircraft : EditText
    val person: ArrayList<Menu> = ArrayList()
    private var root: View? = null
    lateinit var viewRoot : View;
    private lateinit var data :Test
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = HomeViewModel()
         root = inflater.inflate(R.layout.fragment_home, container, false)

//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
////            viewLifecycleOwner
//            textView.text = it
//        })

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        data = Test();
//        bindingViews()

    }
    override fun onResume() {
        super.onResume()
        homeViewModel = HomeViewModel()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addData()
        menu_list.layoutManager = LinearLayoutManager(this.context!!)
        menu_list.adapter = MenuAdapter(person, this.context!!.applicationContext.applicationContext) { item:Menu -> itemClick(item) }


    }

    private fun itemClick(item: Menu){
        Log.println(Log.DEBUG,"HomeFragment","name ${item.nameMenu}")
        Toast.makeText(this.context,"Click ${item.nameMenu}",Toast.LENGTH_LONG).show()
    }

    private fun addData(){
        var name: ArrayList<String> = arrayListOf<String>("menu1","menu2","menu3")
        for( item in name ) {
            var model: Menu = Menu(item, "Hi")
            person.add(model)
        }

    }
}