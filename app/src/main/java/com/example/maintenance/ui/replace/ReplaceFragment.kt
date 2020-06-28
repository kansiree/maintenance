package com.example.maintenance.ui.replace

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maintenance.R


class ReplaceFragment : Fragment() {

    companion object {
        fun newInstance() = ReplaceFragment()
    }

    private lateinit var viewModel: ReplaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.replace_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReplaceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
