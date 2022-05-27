package com.danc.petting.di

import com.danc.petting.data.repository.PetsRepositoryImpl
import com.danc.petting.data.service.PetsService
import com.danc.petting.domain.repositories.PetsRepository
import com.danc.petting.utils.Constants
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder().addInterceptor(
        loggingInterceptor
    ).build()

    @Provides
    @Singleton
    fun providePetsAPI(): PetsService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PetsService::class.java)

    }

    @Provides
    @Singleton
    fun providesPetsRepository(service: PetsService): PetsRepository {
        return PetsRepositoryImpl(service)
    }

}