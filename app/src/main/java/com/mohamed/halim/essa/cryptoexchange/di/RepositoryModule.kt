package com.mohamed.halim.essa.cryptoexchange.di

import com.google.gson.GsonBuilder
import com.mohamed.halim.essa.cryptoexchange.data.Repository
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
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): Repository {
        return Repository(apiService);
    }
}