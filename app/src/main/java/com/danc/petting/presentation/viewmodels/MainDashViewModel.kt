package com.danc.petting.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.danc.petting.data.pagination.DogsDataSource
import com.danc.petting.data.service.PetsService
import com.danc.petting.domain.models.PetsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainDashViewModel @Inject constructor(private val petsService: PetsService) : ViewModel() {

    fun listData(): Flow<PagingData<PetsItem>> =
        Pager(PagingConfig(pageSize = 10, maxSize = 100, enablePlaceholders = false)) {
            DogsDataSource(petsService)
        }.flow.cachedIn(viewModelScope)
    }
