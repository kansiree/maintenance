package com.example.maintenance.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maintenance.*
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
        addData()
    }
    override fun onResume() {
        super.onResume()
        homeViewModel = HomeViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        menu_list.layoutManager = LinearLayoutManager(this.context!!)
        menu_list.adapter = MenuAdapter(person, this.context!!.applicationContext.applicationContext) { item:Menu -> itemClick(item) }
    }

    private fun itemClick(item: Menu){
        Log.println(Log.DEBUG,"HomeFragment","name ${item.nameMenuEng}")

        if (item.nameMenuEng.equals(resources.getStringArray(R.array.menu_eng)[0]))
        {
            addFragment(AircraftFragment(),true,"aircraft")
        }
        else if (item.nameMenuEng.equals(resources.getStringArray(R.array.menu_eng)[1])) {
            addFragment(TroubleFragment(),true,"trouble")
        }
        else if (item.nameMenuEng.equals(resources.getStringArray(R.array.menu_eng)[2])){
            addFragment(ReplaceFragment(),true,"trouble")
        }
        else if (item.nameMenuEng.equals(resources.getStringArray(R.array.menu_eng)[3])){
            addFragment(RemarkFragment(),true,"trouble")
        }
        else{
            addFragment(HomeFragment(),true,"home")
        }
        Toast.makeText(this.context,"Click ${item.nameMenuEng}",Toast.LENGTH_SHORT).show()
    }

    private fun addData(){
        var nameEng: Array<out String> = resources.getStringArray(R.array.menu_eng)
        var nameTH: Array<out String> = resources.getStringArray(R.array.menu_th)
        for( i in nameEng.indices) {
            var model: Menu = Menu(nameEng[i],nameTH[i], "Hi")
            person.add(model)
        }
    }

    private fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager = parentFragmentManager
        val ft = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.nav_host_fragment, fragment, tag)
        ft.commitAllowingStateLoss()
    }
}