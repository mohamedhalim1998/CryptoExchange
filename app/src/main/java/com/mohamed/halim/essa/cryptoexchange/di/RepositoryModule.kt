package com.mohamed.halim.essa.cryptoexchange.di

import com.mohamed.halim.essa.cryptoexchange.data.Repository
import com.mohamed.halim.essa.cryptoexchange.data.local.CryptoDao
import com.mohamed.halim.essa.cryptoexchange.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService, cryptoDao: CryptoDao): Repository {
        return Repository(apiService, cryptoDao);
    }
}