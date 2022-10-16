package com.msharibahmed.shaadidotcomassignment.data.remote.remoteMatchRepository

import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.local.localMatchRepository.LocalMatchRepository
import com.msharibahmed.shaadidotcomassignment.data.local.toEntityMapper.ToEntityMapper
import com.msharibahmed.shaadidotcomassignment.data.remote.api.IApiService
import com.msharibahmed.shaadidotcomassignment.utils.events.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteMatchRepositoryImpl @Inject constructor(
    private val apiService: IApiService,
    private val localMatchRepository: LocalMatchRepository,
    private val mapper: ToEntityMapper
) : RemoteMatchRepository {

    override suspend fun getMatchesFromRemote(): Flow<ResponseState<List<MatchProfile>>> =
        flow {
            val matchProfiles = localMatchRepository.getMatchProfilesFromDb()
            emit(ResponseState.Loading)
            try {
                val matchResponse =
                    apiService.getAllMatches().body()
                //
                localMatchRepository.insertMatchProfiles(
                    mapper.matchRemoteResponseToEntityMapper(
                        matchResponse
                    )
                )
                //
                emit(ResponseState.Success(matchProfiles.first()))
            } catch (e: Exception) {
                emit(ResponseState.Error(e, matchProfiles.first()))
            }
        }


}