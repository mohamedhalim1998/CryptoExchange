package com.mohamed.halim.essa.cryptoexchange.di

import com.google.gson.GsonBuilder
import com.mohamed.halim.essa.cryptoexchange.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkSourceModule {
    @Singleton
    @Provides
    fun provideNetworkSource(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://rest.coinapi.io/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }


}