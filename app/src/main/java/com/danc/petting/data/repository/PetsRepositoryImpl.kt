package com.danc.petting.data.repository

import com.danc.petting.data.service.PetsService
import com.danc.petting.domain.models.Pets
import com.danc.petting.domain.repositories.PetsRepository
import javax.inject.Inject

class PetsRepositoryImpl @Inject constructor(private val petsService: PetsService): PetsRepository {

    override suspend fun getDogs(): Pets {
        TODO("Not yet implemented")
    }


}