package com.msharibahmed.shaadidotcomassignment.data.repository

import com.msharibahmed.shaadidotcomassignment.data.local.dao.MatchDao
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.remote.api.IApiService
import com.msharibahmed.shaadidotcomassignment.data.remote.model.MatchResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val apiService: IApiService,
    private val matchDao: MatchDao
) : MatchRepository {
    override fun getMatchProfilesFromDb(): Flow<List<MatchProfile>> = matchDao.getAll()

    override suspend fun getMatchesFromRemote(): Response<MatchResponseModel> =
        apiService.getAllMatches()


    override suspend fun insertMatchProfiles(matches: List<MatchProfile>) =
        matchDao.insertAll(matches)


    override suspend fun changeMatchStatus(matchProfile: MatchProfile) =
        matchDao.update(uuid = matchProfile.uuid, status = matchProfile.status)

}