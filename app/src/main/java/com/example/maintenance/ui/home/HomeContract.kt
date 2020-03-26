package com.example.maintenance.ui.home

import com.example.maintenance.data.model.Menu
import com.example.maintenance.ui.base.BasePresenter
import com.example.maintenance.ui.base.BaseView
import com.google.android.gms.auth.api.signin.GoogleSignInClient

interface HomeContract {

    interface View : BaseView {
        fun initList(people: MutableList<Menu>)
        fun showPeople()
        fun showName(username : String)
        fun launchAuthentication(client : GoogleSignInClient)
    }

    interface Presenter : BasePresenter {
        fun startAuthentication()
        fun loginSuccessful()
        fun loginFailed()
    }
}