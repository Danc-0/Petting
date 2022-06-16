package com.danc.petting.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danc.petting.data.service.PetsService
import com.danc.petting.domain.models.PetsItem
import javax.inject.Inject

class DogsDataSource @Inject constructor(private val petsService: PetsService): PagingSource<Int, PetsItem>() {
    override fun getRefreshKey(state: PagingState<Int, PetsItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PetsItem> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = petsService.getDogs(10, currentLoadingPageKey)
            val responseData = mutableListOf<PetsItem>()
            val data = response.body()?.pets ?: emptyList()
            responseData.addAll(data)

            val previousKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = previousKey,
                nextKey = currentLoadingPageKey.plus(1)
            )

        } catch (e: Exception){
            return LoadResult.Error(e)
        }
    }

}