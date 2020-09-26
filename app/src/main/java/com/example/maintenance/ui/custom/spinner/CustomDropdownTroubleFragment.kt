package com.example.maintenance.ui.custom.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.maintenance.R
import com.example.maintenance.data.model.Master

class CustomDropdownTroubleFragment(val context: Context, private val dataSource: List<Master>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View
        val vh: ItemHolder
        if (convertView == null) {

            view = inflater.inflate(R.layout.custom_spinner_trouble_fragment, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource[position].full_name
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
        val label: TextView = row?.findViewById(R.id.txt_spinner_trouble) as TextView
        val checkBox: CheckBox = row?.findViewById(R.id.trouble_checkbox) as CheckBox
    }
}