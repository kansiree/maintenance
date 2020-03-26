package com.example.maintenance.ui.home

import com.example.maintenance.data.manager.AuthenticationManager
import com.example.maintenance.data.model.Menu
import com.example.maintenance.data.respository.sheets.SheetsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * @author Pedro Carrillo
 */

class ReadSpreadsheetPresenter(private val view: HomeContract.View,
                               private val authenticationManager: AuthenticationManager,
                               private val sheetsRepository: SheetsRepository
) :
    HomeContract.Presenter {

    private lateinit var readSpreadsheetDisposable : Disposable
    private val people : MutableList<Menu> = mutableListOf()

    override fun startAuthentication() {
        view.launchAuthentication(authenticationManager.googleSignInClient)
    }

    override fun init() {
        startAuthentication()

        view.initList(people)
    }

    override fun dispose() {
        readSpreadsheetDisposable.dispose()
    }

    override fun loginSuccessful() {
        view.showName(authenticationManager.getLastSignedAccount()?.displayName!!)
        authenticationManager.setUpGoogleAccountCredential()
        startReadingSpreadsheet(spreadsheetId, range)
    }

    override fun loginFailed() {

    }

    private fun startReadingSpreadsheet(spreadsheetId : String, range : String) {
        people.clear()
        readSpreadsheetDisposable=
                sheetsRepository.readSpreadSheet(spreadsheetId, range)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { view.showError(it.message!!) }
                .subscribe(Consumer {
                    people.addAll(it)
                    view.showPeople()
                })

    }

    companion object {
//        val spreadsheetId = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms"
        var spreadsheetId = "13wR1YqV2J7NoC-C687rJPMKKRxvVGOw2zPj3mQJVNco"
        val range = "Class Data!A2:E"

    }
}
