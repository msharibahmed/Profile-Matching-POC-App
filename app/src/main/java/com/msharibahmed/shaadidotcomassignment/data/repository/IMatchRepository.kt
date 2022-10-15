package com.msharibahmed.shaadidotcomassignment.data.repository

import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.remote.model.MatchResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IMatchRepository {
    fun getMatchProfilesFromDb(): Flow<List<MatchProfile>> //local data

    suspend fun getMatchesFromRemote(): Response<MatchResponseModel> //remote response data

    suspend fun insertMatchProfiles(matches: List<MatchProfile>) //insert data to local db

    suspend fun changeMatchStatus(matchProfile: MatchProfile) //update match profile status

}
