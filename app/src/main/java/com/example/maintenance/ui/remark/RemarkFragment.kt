package com.example.maintenance.ui.remark

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maintenance.R


class RemarkFragment : Fragment() {

    companion object {
        fun newInstance() = RemarkFragment()
    }

    private lateinit var viewModel: RemarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.remark_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RemarkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
