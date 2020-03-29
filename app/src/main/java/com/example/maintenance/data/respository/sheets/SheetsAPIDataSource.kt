package com.example.maintenance.data.respository.sheets

import com.example.maintenance.data.manager.AuthenticationManager
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.Spreadsheet
import com.example.maintenance.data.model.Menu
import com.example.maintenance.data.model.SpreadsheetInfo

import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author Pedro Carrillo.
 */
class SheetsAPIDataSource(private val authManager : AuthenticationManager,
                          private val transport : HttpTransport,
                          private val jsonFactory: JsonFactory) : SheetsDataSource {

    private val sheetsAPI : Sheets
        get() {
            return Sheets.Builder(transport,
                jsonFactory,
                authManager.googleAccountCredential)
                .setApplicationName("test")
                .build()
        }

    override fun readSpreadSheet(spreadsheetId: String,
                                 spreadsheetRange: String): Single<List<Menu>> {
        return Observable
            .fromCallable{
                val response = sheetsAPI.spreadsheets().values()
                    .get(spreadsheetId, spreadsheetRange)
                    .execute()
                response.getValues() }
            .flatMapIterable { it -> it }
            .map { Menu(it[0].toString(),it[2].toString(), it[4].toString()) }
            .toList()
    }

    override fun createSpreadsheet(spreadSheet : Spreadsheet) : Observable<SpreadsheetInfo> {
        return Observable
            .fromCallable{
                val response =
                    sheetsAPI
                        .spreadsheets()
                        .create(spreadSheet)
                        .execute()
                response }
            .map { SpreadsheetInfo(it[KEY_ID] as String, it[KEY_URL] as String) }
    }

    companion object {
        val KEY_ID = "spreadsheetId"
        val KEY_URL = "spreadsheetUrl"
    }
}