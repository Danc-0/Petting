package com.danc.petting.data.service

import com.danc.petting.domain.models.Pets
import retrofit2.http.GET

interface PetsService {

    @GET("/breeds")
    suspend fun getDogs() : Pets

}