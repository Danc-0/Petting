package com.danc.petting.domain.repositories

import com.danc.petting.domain.models.Pets

interface PetsRepository {

    suspend fun getDogs(): Pets

}