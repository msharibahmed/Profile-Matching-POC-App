package com.msharibahmed.shaadidotcomassignment.di

import com.msharibahmed.shaadidotcomassignment.data.repository.MatchRepository
import com.msharibahmed.shaadidotcomassignment.data.repository.MatchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsMatchRepository(matchRepository: MatchRepositoryImpl): MatchRepository
}
