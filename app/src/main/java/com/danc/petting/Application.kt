package com.danc.petting

import android.app.Application
import com.danc.petting.data.room.DogsDatabase
import com.danc.petting.domain.repositories.LocalDogsRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class Application: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { DogsDatabase.getDogsDatabase(this, applicationScope) }
    val localDogsRepository by lazy { LocalDogsRepository(database.dogsDao()) }

}