package com.danc.petting.domain.usecases

import com.danc.petting.data.service.PetsService
import com.danc.petting.domain.models.Pets
import com.danc.petting.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class DogsUseCase @Inject constructor(private val service: PetsService) {

    operator fun invoke(): Flow<Resource<Response<Pets>>> = flow {
        try {
            emit(Resource.Loading())
            val pets = service.getDogs(10, 1)
            emit(Resource.Success(pets))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resource.Error("We couldn't reach the servers. Check your internet connection"))
        }
    }

}