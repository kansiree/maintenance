package com.example.maintenance.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Pedro Carrillo.
 */
abstract class BaseActivity<P : BasePresenter>  : AppCompatActivity() {

    lateinit var presenter : P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    abstract fun initDependencies()

}