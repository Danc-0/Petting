package com.danc.petting.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.danc.petting.R
import com.danc.petting.utils.Network
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGetStarted.setOnClickListener {
            if (context?.let { it1 -> Network().checkForInternet(it1) } == true){
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_mainDashFragment)
            } else {
                Snackbar.make(view, "Please check your internet connection and TRY AGAIN", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}