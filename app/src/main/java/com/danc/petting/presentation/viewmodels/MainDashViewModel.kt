package com.danc.petting.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.danc.petting.data.pagination.DogsDataSource
import com.danc.petting.data.service.PetsService
import com.danc.petting.domain.models.Pets
import com.danc.petting.domain.usecases.DogsUseCase
import com.danc.petting.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainDashViewModel @Inject constructor(private val petsService: PetsService): ViewModel() {

    val pets = MutableLiveData<Resource<Pets>>()

    val listData = Pager(PagingConfig(pageSize = 100)){
        DogsDataSource(petsService)
    }.flow.cachedIn(viewModelScope)

//    fun getPets() {
//        dogsUseCase().onEach {
//            kotlin.run {
//                when(it) {
//                    is Resource.Loading -> {
//                        Log.d("TAG", "getPets: Loading please wait")
//                    }
//
//                    is Resource.Success -> {
//                        pets.value = it
////                        pets.postValue(it)
//                    }
//
//                    is Resource.Error -> {
//                        Log.d("TAG", "getPets: An error occurred")
//                    }
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

}