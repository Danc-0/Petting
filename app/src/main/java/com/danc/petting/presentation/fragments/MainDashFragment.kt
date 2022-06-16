package com.danc.petting.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.danc.petting.R
import com.danc.petting.domain.models.Pets
import com.danc.petting.presentation.adapters.MainDashAdapter
import com.danc.petting.presentation.viewmodels.MainDashViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_main_dash.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainDashFragment : Fragment(R.layout.fragment_main_dash) {

    private val viewModel: MainDashViewModel by viewModels()
    private var mainDashAdapter: MainDashAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
//        viewModel.getPets()
//        viewModel.pets.observe(viewLifecycleOwner) { pets ->
//            adapter = MainDashAdapter(pets.data)
//            rvDogsList.adapter = adapter
//            rvDogsList.layoutManager = GridLayoutManager(context, 2)

//        }



    }

    private fun setUpView() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                mainDashAdapter = MainDashAdapter()
                rvDogsList.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = mainDashAdapter
                }
//                rvDogsList.adapter = mainDashAdapter
//                rvDogsList.layoutManager = GridLayoutManager(context, 2)

            }
        }
    }

}