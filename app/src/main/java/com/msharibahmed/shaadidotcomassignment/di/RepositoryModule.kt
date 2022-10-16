package com.msharibahmed.shaadidotcomassignment.di

import com.msharibahmed.shaadidotcomassignment.data.local.localMatchRepository.LocalMatchRepository
import com.msharibahmed.shaadidotcomassignment.data.local.localMatchRepository.LocalMatchRepositoryImpl
import com.msharibahmed.shaadidotcomassignment.data.remote.remoteMatchRepository.RemoteMatchRepository
import com.msharibahmed.shaadidotcomassignment.data.remote.remoteMatchRepository.RemoteMatchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsRemoteMatchRepository(remoteMatchRepository: RemoteMatchRepositoryImpl): RemoteMatchRepository

    @Binds
    fun bindsLocalMatchRepository(localMatchRepository: LocalMatchRepositoryImpl): LocalMatchRepository
}
