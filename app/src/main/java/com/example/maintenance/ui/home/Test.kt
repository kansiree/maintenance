package com.example.maintenance.ui.home

import android.content.Intent
import com.example.maintenance.data.manager.AuthenticationManager
import com.example.maintenance.data.model.Menu
import com.example.maintenance.data.respository.sheets.SheetsAPIDataSource
import com.example.maintenance.data.respository.sheets.SheetsRepository
import com.example.maintenance.ui.base.BaseActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.android.gms.drive.Drive
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.sheets.v4.SheetsScopes
import java.util.*

open class Test : BaseActivity<HomeContract.Presenter>(),HomeContract.View {

    override fun initList(people: MutableList<Menu>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPeople() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showName(username: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun launchAuthentication(client: GoogleSignInClient) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == HomeFragment.RQ_GOOGLE_SIGN_IN) {
//            if (resultCode == Activity.RESULT_OK) {
//                presenter.loginSuccessful()
//            } else {
//                presenter.loginFailed()
//            }
//        }
    }

    override fun initDependencies() {
        val signInOptions : GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Scope(SheetsScopes.SPREADSHEETS_READONLY))
                .requestScopes(Scope(SheetsScopes.SPREADSHEETS))
                .requestScopes(Drive.SCOPE_FILE)
                .requestEmail()
                .build()
        val googleSignInClient = GoogleSignIn.getClient(this, signInOptions)
        val googleAccountCredential = GoogleAccountCredential
            .usingOAuth2(this, Arrays.asList(*AuthenticationManager.SCOPES))
            .setBackOff(ExponentialBackOff())
        val authManager =
            AuthenticationManager(
                lazyOf(this),
                googleSignInClient,
                googleAccountCredential)
        val sheetsApiDataSource =
            SheetsAPIDataSource(authManager,
                AndroidHttp.newCompatibleTransport(),
                JacksonFactory.getDefaultInstance())
        val sheetsRepository = SheetsRepository(sheetsApiDataSource)
        presenter = ReadSpreadsheetPresenter(this, authManager, sheetsRepository)

    }

    companion object {
        const val TAG = "ReadSpreadsheetActivity"
        const val RQ_GOOGLE_SIGN_IN = 999
    }
}