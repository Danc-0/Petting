package com.danc.petting.domain.repositories

import androidx.annotation.WorkerThread
import com.danc.petting.data.room.DogsDao
import com.danc.petting.domain.models.local.LocalPetsItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDogsRepository @Inject constructor(private val dogsDao: DogsDao) {

    val allDogs: Flow<List<LocalPetsItem>> = dogsDao.getAllFavouriteDogs()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(localPetsItem: LocalPetsItem) {
        dogsDao.addFavourite(localPetsItem)
    }

}