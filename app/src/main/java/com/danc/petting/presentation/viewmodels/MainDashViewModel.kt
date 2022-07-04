package com.danc.petting.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.danc.petting.data.pagination.DogsDataSource
import com.danc.petting.data.service.PetsService
import com.danc.petting.domain.models.PetsItem
import com.danc.petting.domain.models.local.LocalPetsItem
import com.danc.petting.domain.repositories.LocalDogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainDashViewModel @Inject constructor(
    private val petsService: PetsService,
    private val localDogsRepository: LocalDogsRepository
) : ViewModel() {

    fun listData(): Flow<PagingData<PetsItem>> =
        Pager(PagingConfig(pageSize = 10, maxSize = 100, enablePlaceholders = false)) {
            DogsDataSource(petsService)
        }.flow.cachedIn(viewModelScope)

    val allFavouriteDogs: LiveData<List<LocalPetsItem>> = localDogsRepository.allDogs.asLiveData()

    fun addFavourite(localPetsItem: LocalPetsItem) = viewModelScope.launch {
        localDogsRepository.insert(localPetsItem)
    }

}

