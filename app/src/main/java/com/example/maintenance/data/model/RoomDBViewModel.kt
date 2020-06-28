package com.example.maintenance.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maintenance.data.api.ApiHelper
import com.example.maintenance.data.database.DatabaseHelper
import com.example.maintenance.data.database.entity.MasterSystem
import com.example.maintenance.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class RoomDBViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
):ViewModel() {

    private val masters = MutableLiveData<Resource<List<MasterSystem>>>()
    init {
        fetchMasterSystem()
    }

    private fun fetchMasterSystem(){
        try {
            viewModelScope.launch { val masterSystemFromDb = dbHelper.getMasterSystem() }
        }
        catch (e:Exception){
            Log.e("RoomDBViewModel",e.toString())
        }
    }

    fun getMasterSystem(): LiveData<Resource<List<MasterSystem>>>{
        return masters
    }
}