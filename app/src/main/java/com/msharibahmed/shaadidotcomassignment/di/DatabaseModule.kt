package com.msharibahmed.shaadidotcomassignment.di

import android.content.Context
import androidx.room.Room
import com.msharibahmed.shaadidotcomassignment.data.local.localDb.MatchDatabase
import com.msharibahmed.shaadidotcomassignment.utils.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ) : MatchDatabase = Room.databaseBuilder(appContext, MatchDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMatchDao(database: MatchDatabase) = database.matchDao()
}
