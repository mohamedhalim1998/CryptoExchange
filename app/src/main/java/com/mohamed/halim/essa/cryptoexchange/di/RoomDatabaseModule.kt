package com.mohamed.halim.essa.cryptoexchange.di

import android.content.Context
import androidx.room.Room
import com.mohamed.halim.essa.cryptoexchange.data.local.CryptoDao
import com.mohamed.halim.essa.cryptoexchange.data.local.CryptoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): CryptoDao {
        val db = Room.databaseBuilder(
            context,
            CryptoDatabase::class.java, "database-name"
        ).build()
        return db.cryptoDao()
    }
}