package com.danc.petting.data.service

import com.danc.petting.domain.models.Pets
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PetsService {

    @GET("breeds")
    suspend fun getDogs(@Query("limit") limit: Int, @Query("page") pageNumber: Int) : Response<Pets>

    @GET("categories")
    suspend fun getCategories() : Pets

}