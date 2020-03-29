package com.example.maintenance.ui.adapter

import android.content.Context
import android.net.TrafficStats
import android.opengl.GLSurfaceView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.example.maintenance.R.layout
import com.example.maintenance.data.model.Menu
import kotlinx.android.synthetic.main.main_menu_list_item.view.*
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import java.util.zip.InflaterOutputStream


class MenuAdapter(val item:ArrayList<Menu>, val context: Context,val clickListener: (Menu) ->  Unit) : RecyclerView.Adapter<MenuViewHolder>() {
val Tag:String = "MenuAdapter"
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(LayoutInflater.from(context).inflate(layout.main_menu_list_item,parent,false))
    }

    override fun getItemCount(): Int {
        print("item size: "+item.size)

        return item.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        (holder as MenuViewHolder).bind(item[position],clickListener)
//        holder?.menuTxt?.text = item.get(position).nameMenu
    }


}

class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val Tag:String = "MenuViewHolder"

    fun bind(data:Menu,clickListener: (Menu) -> Unit){
        Log.println(Log.DEBUG, Tag,"data ${ data.nameMenuEng }" )
        itemView.setOnClickListener{clickListener(data)}
        itemView.menu_eng_txt.text = data.nameMenuEng
        itemView.menu_th_txt.text = data.nameMenuTH
    }
//    val menuTxt = itemView.menu_txt



}
