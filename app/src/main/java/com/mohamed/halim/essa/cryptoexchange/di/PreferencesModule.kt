package com.mohamed.halim.essa.cryptoexchange.di

import android.content.Context
import com.mohamed.halim.essa.cryptoexchange.prefstore.PrefsStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): PrefsStoreManager {
        return PrefsStoreManager(context)
    }
}