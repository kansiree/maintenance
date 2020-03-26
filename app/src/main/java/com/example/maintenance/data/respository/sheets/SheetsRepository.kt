package com.example.maintenance.data.respository.sheets


import com.example.maintenance.data.model.Menu
import com.example.maintenance.data.model.SpreadsheetInfo
import com.google.api.services.sheets.v4.model.Spreadsheet

import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author Pedro Carrillo.
 */
class SheetsRepository(private val sheetsAPIDataSource: SheetsAPIDataSource) {


    fun readSpreadSheet(spreadsheetId : String,
                        spreadsheetRange : String): Single<List<Menu>> {
        return sheetsAPIDataSource.readSpreadSheet(spreadsheetId, spreadsheetRange)
    }

    fun createSpreadsheet(spreadSheet : Spreadsheet) : Observable<SpreadsheetInfo>  {
        return sheetsAPIDataSource.createSpreadsheet(spreadSheet)
    }

}