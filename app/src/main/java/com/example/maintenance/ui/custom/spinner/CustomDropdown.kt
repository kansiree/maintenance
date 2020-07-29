package com.example.maintenance.ui.custom.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.maintenance.R
import com.example.maintenance.data.database.entity.MasterAircraft
import com.example.maintenance.data.database.entity.MasterSystem
import com.example.maintenance.data.model.Master
import com.example.maintenance.data.model.responseapi.MasterResponse
import io.reactivex.internal.functions.Functions


class CustomDropdown(val context: Context, val dataSource: List<Master>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


//    init {
//        if (dataSource1 is List<*>) {
//            dataSource = dataSource1.filterIsInstance<Master>()
//
//        }
//        for ( item  in dataSource1) {
//            Master(item.id)
//            dataSource.add(item as Master)
//            println(item.full_name)
//            // body of loop
//        }
//    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custom_spinner, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource.get(position).full_name

        return view
    }

    override fun getItem(position: Int): Any {
       return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private class ItemHolder(row: View?) {
        val label: TextView

        init {
            label = row?.findViewById(R.id.txt_spinner) as TextView
        }
    }
}




