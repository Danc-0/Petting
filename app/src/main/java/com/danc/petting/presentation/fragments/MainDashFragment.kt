package com.danc.petting.presentation.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainDashFragment : Fragment(R.layout.fragment_main_dash) {

    private val viewModel: MainDashViewModel by viewModels()
    private var mainDashAdapter = MainDashAdapter()
    private var gridLayoutSpan = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
        mainDashAdapter = MainDashAdapter()
        val currentOrientation = resources.configuration.orientation
        gridLayoutSpan = if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            3
        } else {
            2
        }
        rvDogsList.apply {
            layoutManager = GridLayoutManager(context, gridLayoutSpan)
            setHasFixedSize(true)
            adapter = mainDashAdapter
        }
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.listData().collectLatest {
                Log.d("TAG", "listData1: $it")
                Log.d("TAG", "listData6: $mainDashAdapter")
                mainDashAdapter.submitData(it)
                Log.d("TAG", "listData7: $mainDashAdapter")

            }

        }

    }
}