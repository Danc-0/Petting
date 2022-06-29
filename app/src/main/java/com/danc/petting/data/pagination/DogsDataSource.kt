package com.danc.petting.data.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danc.petting.data.service.PetsService
import com.danc.petting.domain.models.PetsItem
import javax.inject.Inject


class DogsDataSource @Inject constructor(private val petsService: PetsService): PagingSource<Int, PetsItem>() {

    override fun getRefreshKey(state: PagingState<Int, PetsItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PetsItem> {
        val currentLoadingPageKey = params.key ?: FIRST_PAGE_INDEX
        try {
            val response = petsService.getDogs(10, currentLoadingPageKey)

            val previousKey = if (currentLoadingPageKey < 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = response,
                prevKey = previousKey,
                nextKey = currentLoadingPageKey.plus(1)
            )

        } catch (e: Exception){
            return LoadResult.Error(e)
        }
    }

    companion object {
       private const val FIRST_PAGE_INDEX = 0
    }

}