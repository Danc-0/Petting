package com.danc.petting.data.service

import com.danc.petting.domain.models.Pets
import com.danc.petting.domain.models.PetsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PetsService {

    @GET("breeds")
    suspend fun  getDogs(@Query("limit") limit: Int, @Query("page") pageNumber: Int) : List<PetsItem>

    @GET("categories")
    suspend fun getCategories() : Pets

}