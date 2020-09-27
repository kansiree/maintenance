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
    private val masterAircraft = MutableLiveData<Resource<List<MasterAircraft>>>()
    private val masterSystem = MutableLiveData<Resource<List<MasterSystem>>>()
    private val masterTechOrder = MutableLiveData<Resource<List<MasterTecOrder>>>()

    private val response = MutableLiveData<Resource<Status>>()

    init {
        fetchMasterSystem()
    }

    private fun fetchMasterSystem(){
        response.postValue(Resource.loading(null))
        viewModelScope.launch {
         try {
             Log.i(TAG,"insertMaster")

             dbHelper.deleteMaster()
             insertMasterAircraft()
             insertMasterSystem()
             insertMasterTechnical()
             response.postValue(Resource.success(null))

            } catch (e: Exception) {
                Log.e(TAG,e.printStackTrace().toString())
             response.postValue(Resource.error(e.toString(),null))
            }finally {
             Log.i(TAG,"insert master success")

         }

        }

    }

    fun getStatusInsertMaster(): LiveData<Resource<Status>>{
        return response
    }

    fun getMasterAircraft(): LiveData<Resource<List<MasterAircraft>>>{
        return masterAircraft
    }

    fun getMasterSystem(): LiveData<Resource<List<MasterSystem>>>{
        return masterSystem
    }

    fun getMasterTechnicalOrder(): LiveData<Resource<List<MasterTecOrder>>>{
        return masterTechOrder
    }
    
    private suspend fun insertMasterAircraft(){
        try {
            masterAircraft.postValue(Resource.loading(null))
            val usersFromDb = dbHelper.getMasterAircraft()
            Log.i(TAG,"insertMasterAircraft")

            if (usersFromDb.isEmpty()) {
                val systemFromApi = apiHelper.getMasterAircraft()
                val systemToInsertInDB = mutableListOf<MasterAircraft>()

                for(apiSystem in systemFromApi.message){
                    val system = MasterAircraft(
                        apiSystem.id,
                        apiSystem.fullName,
                        apiSystem.createDate
                    )
                    systemToInsertInDB.add(system)
                }
                dbHelper.insertMaster(systemToInsertInDB)
                masterAircraft.postValue(Resource.success(systemToInsertInDB))

            } else {
                masterAircraft.postValue(Resource.success(usersFromDb))
            }
        }catch (e:Exception){
            throw e
        }
    }
    
    private suspend fun insertMasterSystem(){
        try {
            masterSystem.postValue(Resource.loading(null))
            val usersFromDb1 = dbHelper.getMasterSystem()
            Log.i(TAG,"insertMasterSystem")

            if (usersFromDb1.isEmpty()) {
                val systemFromApi = apiHelper.getMasterSystem()
                val systemToInsertInDB = mutableListOf<MasterSystem>()

                for(apiSystem in systemFromApi.message){
                    val system = MasterSystem(
                        apiSystem.id,
                        apiSystem.fullName,
                        apiSystem.createDate
                    )
                    systemToInsertInDB.add(system)
                }
                dbHelper.insertMasterSystem(systemToInsertInDB)
                masterSystem.postValue(Resource.success(systemToInsertInDB))

            } else {
                masterSystem.postValue(Resource.success(usersFromDb1))
            }
        }catch (e:Exception){
            throw e
        }
        
    }
  
    private suspend fun insertMasterTechnical(){
        try {
            masterTechOrder.postValue(Resource.loading(null))
            val usersFromDb2 = dbHelper.getMasterTechnicalOrder()
            Log.i(TAG,"insertMasterTechnical")

            if (usersFromDb2.isEmpty()) {
                val systemFromApi = apiHelper.getMasterTechnicalOrder()
                val systemToInsertInDB = mutableListOf<MasterTecOrder>()

                for(apiSystem in systemFromApi.message){
                    systemToInsertInDB.add(
                        MasterTecOrder(
                            apiSystem.id,
                            apiSystem.fullName,
                            apiSystem.createDate
                        )
                    )
                }
                dbHelper.insertTechnicalOrder(systemToInsertInDB)
                masterTechOrder.postValue(Resource.success(systemToInsertInDB))

            } else {
                masterTechOrder.postValue(Resource.success(usersFromDb2))
            }
        }catch (e:Exception){
            throw e
        }

    }

}