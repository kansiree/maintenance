package com.example.maintenance.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.maintenance.data.repository.AircraftRepository

class HomeViewModel (application: Application) : AndroidViewModel(application) {
    private var repository:AircraftRepository = AircraftRepository(application)


}