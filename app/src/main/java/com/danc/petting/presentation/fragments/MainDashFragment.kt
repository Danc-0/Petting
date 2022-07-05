package com.danc.petting.presentation.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.danc.petting.R
import com.danc.petting.domain.models.Pets
import com.danc.petting.domain.models.PetsItem
import com.danc.petting.domain.models.local.LocalPetsItem
import com.danc.petting.presentation.adapters.MainDashAdapter
import com.danc.petting.presentation.viewmodels.MainDashViewModel
import com.google.android.material.snackbar.Snackbar
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
class MainDashFragment : Fragment(R.layout.fragment_main_dash),
    MainDashAdapter.AddToFavouriteCallBack {

    private val viewModel: MainDashViewModel by viewModels()
    private var mainDashAdapter: MainDashAdapter? = null
    private var gridLayoutSpan = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
        mainDashAdapter = MainDashAdapter(this)
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
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.listData().collectLatest {
                Handler(Looper.getMainLooper()).postDelayed({
                    shimmerFrameLayout.stopShimmerAnimation()
                    shimmerFrameLayout.visibility = View.GONE
                    rvDogsList.visibility = View.VISIBLE

                }, 1000)
                mainDashAdapter?.submitData(it)

            }

        }

        getAllFavourites()

    }

    override fun addToFav(petsItem: PetsItem?) {
        val localPetsItem = LocalPetsItem(
            petsItem?.id,
            petsItem?.bred_for,
            petsItem?.breed_group,
            petsItem?.life_span,
            petsItem?.name,
            petsItem?.origin,
            petsItem?.reference_image_id,
            petsItem?.temperament,
            petsItem?.weight?.imperial,
            petsItem?.weight?.metric,
            petsItem?.height?.imperial,
            petsItem?.height?.metric
        )
        viewModel.addFavourite(localPetsItem)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_mainDashFragment_to_favouritesFragment)
    }

    private fun getAllFavourites() {
        viewModel.allFavouriteDogs.observe(viewLifecycleOwner) {

        }
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }
}