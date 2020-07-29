package com.example.maintenance.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maintenance.data.api.ApiHelper
import com.example.maintenance.data.database.DatabaseHelper
import com.example.maintenance.data.database.entity.MasterAircraft
import com.example.maintenance.data.database.entity.MasterSystem
import com.example.maintenance.data.database.entity.MasterTecOrder
import com.example.maintenance.utils.Resource
import com.example.maintenance.utils.Status
import kotlinx.coroutines.launch
import java.lang.Exception

class RoomDBViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
):ViewModel() {

    private val TAG :String = "RoomDBViewModel"
    private val masters = MutableLiveData<Resource<List<MasterAircraft>>>()
    private val masters1 = MutableLiveData<Resource<List<MasterSystem>>>()
    private val masters2 = MutableLiveData<Resource<List<MasterTecOrder>>>()

    private val response = MutableLiveData<Resource<Status>>()

    init {
        fetchMasterSystem()
    }

    fun fetchMasterSystem(){
        response.postValue(Resource.loading(null))
        viewModelScope.launch {
         try {
             dbHelper.deleteMaster()
             insertMasterAircraft()
             insertMasterSystem()
             insertMasterTechnical()
             response.postValue(Resource.success(null))

            } catch (e: Exception) {
                Log.e("Room",e.printStackTrace().toString())
             response.postValue(Resource.error(e.toString(),null))
            }

        }

    }

    fun getStatusInsertMaster(): LiveData<Resource<Status>>{
        return response
    }

    fun getMasterAircraft(): LiveData<Resource<List<MasterAircraft>>>{
        return masters
    }

    fun getMasterSystem(): LiveData<Resource<List<MasterSystem>>>{
        return masters1
    }

    fun getMasterTechnicalOrder(): LiveData<Resource<List<MasterTecOrder>>>{
        return masters2
    }
    
    private suspend fun insertMasterAircraft(){
        masters.postValue(Resource.loading(null))
        try {
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
        }catch (e:Exception){
            throw e
        }
    }
    
    private suspend fun insertMasterSystem(){
        try {
            masters1.postValue(Resource.loading(null))
            val usersFromDb1 = dbHelper.getMasterSystem()
            if (usersFromDb1.isEmpty()) {
                val systemFromApi = apiHelper.getMasterSystem()
                val systemToInsertInDB = mutableListOf<MasterSystem>()

                for(apiSystem in systemFromApi){
                    val system = MasterSystem(
                        apiSystem.id,
                        apiSystem.fullName,
                        apiSystem.createDate
                    )
                    systemToInsertInDB.add(system)
                }
                dbHelper.insertMasterSystem(systemToInsertInDB)
                masters1.postValue(Resource.success(systemToInsertInDB))

            } else {
                masters1.postValue(Resource.success(usersFromDb1))
            }
        }catch (e:Exception){
            throw e
        }
        
    }
  
    private suspend fun insertMasterTechnical(){
        try {
            masters2.postValue(Resource.loading(null))
            val usersFromDb2 = dbHelper.getMasterTechnicalOrder()
            if (usersFromDb2.isEmpty()) {
                val systemFromApi = apiHelper.getMasterTechnicalOrder()
                val systemToInsertInDB = mutableListOf<MasterTecOrder>()

                for(apiSystem in systemFromApi){
                    systemToInsertInDB.add(
                        MasterTecOrder(
                            apiSystem.id,
                            apiSystem.fullName,
                            apiSystem.createDate
                        )
                    )
                }
                dbHelper.insertTechnicalOrder(systemToInsertInDB)
                masters2.postValue(Resource.success(systemToInsertInDB))

            } else {
                masters2.postValue(Resource.success(usersFromDb2))
            }
        }catch (e:Exception){
            throw e
        }

    }

}