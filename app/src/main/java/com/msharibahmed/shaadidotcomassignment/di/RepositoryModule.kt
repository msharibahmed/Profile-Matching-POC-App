package com.msharibahmed.shaadidotcomassignment.di

import com.msharibahmed.shaadidotcomassignment.data.repository.MatchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun MatchRepository(matchRepository: MatchRepository): MatchRepository
}
