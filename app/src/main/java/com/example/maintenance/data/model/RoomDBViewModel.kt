package com.example.maintenance.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maintenance.data.api.ApiHelper
import com.example.maintenance.data.database.DatabaseHelper
import com.example.maintenance.data.database.entity.MasterAircraft
import com.example.maintenance.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class RoomDBViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
):ViewModel() {

    private val TAG :String = "RoomDBViewModel"
    private val masters = MutableLiveData<Resource<List<MasterAircraft>>>()
    init {
        fetchMasterSystem()
    }

    private fun fetchMasterSystem(){
        viewModelScope.launch {
            masters.postValue(Resource.loading(null))
            try {
                dbHelper.deleteMaster()
                val usersFromDb = dbHelper.getMasterAircraft()
                if (usersFromDb.isEmpty()) {
                    val systemFromApi = apiHelper.getMasterAircraft()
                    val systemToInsertInDB = mutableListOf<MasterAircraft>()

                    for(apiSystem in systemFromApi){
                        val system = MasterAircraft(
                            apiSystem.id,
                            apiSystem.fullName,
                            apiSystem.createDate
                        )
                        systemToInsertInDB.add(system)
                    }
                    dbHelper.insertMaster(systemToInsertInDB)
                    masters.postValue(Resource.success(systemToInsertInDB))

                } else {
                    masters.postValue(Resource.success(usersFromDb))
                }
            } catch (e: Exception) {
                Log.e("Room",e.printStackTrace().toString())
                masters.postValue(Resource.error("Something Went Wrong", null))
            }
            finally {

            }
        }

    }

    fun getMasterAircraft(): LiveData<Resource<List<MasterAircraft>>>{
        return masters
    }
}