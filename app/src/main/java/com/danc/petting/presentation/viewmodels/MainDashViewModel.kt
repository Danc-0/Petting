package com.danc.petting.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danc.petting.domain.models.Pets
import com.danc.petting.domain.usecases.DogsUseCase
import com.danc.petting.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainDashViewModel @Inject constructor(private val dogsUseCase: DogsUseCase): ViewModel() {

    val pets = MutableLiveData<Resource<Pets>>()

    fun getPets() {
        dogsUseCase().onEach {
            kotlin.run {
                when(it) {
                    is Resource.Loading -> {
                        Log.d("TAG", "getPets: Loading please wait")
                    }

                    is Resource.Success -> {
                        pets.value = it
//                        pets.postValue(it)
                    }

                    is Resource.Error -> {
                        Log.d("TAG", "getPets: An error occurred")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}