package com.msharibahmed.shaadidotcomassignment.data.repository

import com.msharibahmed.shaadidotcomassignment.data.local.dao.MatchDao
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.local.toEntityMapper.ToMatchProfileMapper.matchRemoteResponseToEntityMapper
import com.msharibahmed.shaadidotcomassignment.data.remote.api.IApiService
import com.msharibahmed.shaadidotcomassignment.utils.events.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val apiService: IApiService,
    private val matchDao: MatchDao
) : MatchRepository {

    private val matchProfiles = getMatchProfilesFromDb()

    override fun getMatchProfilesFromDb(): Flow<List<MatchProfile>> = matchDao.getAll()

    override suspend fun getMatchesFromRemote(): Flow<ResponseState<List<MatchProfile>>> =
        flow {

            emit(ResponseState.Loading)
            try {
                val matchResponse =
                    apiService.getAllMatches().body()
                //
                matchDao.insertAll(matchRemoteResponseToEntityMapper(matchResponse))
                //
                emit(ResponseState.Success(matchProfiles.first()))
            } catch (e: Exception) {
                emit(ResponseState.Error(e, matchProfiles.first()))
            }
        }


    override suspend fun insertMatchProfiles(matches: List<MatchProfile>) =
        matchDao.insertAll(matches)


    override suspend fun changeMatchStatus(matchProfile: MatchProfile) =
        matchDao.update(uuid = matchProfile.uuid, status = matchProfile.status)


}