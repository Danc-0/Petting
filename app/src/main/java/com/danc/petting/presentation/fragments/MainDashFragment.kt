package com.danc.petting.presentation.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.danc.petting.R
import com.danc.petting.domain.models.Pets
import com.danc.petting.presentation.adapters.MainDashAdapter
import com.danc.petting.presentation.viewmodels.MainDashViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_main_dash.*

@AndroidEntryPoint
class MainDashFragment : Fragment(R.layout.fragment_main_dash) {

    private val viewModel: MainDashViewModel by viewModels()
    private var adapter: MainDashAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPets()
        viewModel.pets.observe(viewLifecycleOwner) { pets ->
            adapter = MainDashAdapter(pets.data)
            rvDogsList.adapter = adapter
            rvDogsList.layoutManager = GridLayoutManager(context, 2)

        }

    }
}