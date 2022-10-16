package com.msharibahmed.shaadidotcomassignment.data.repository

import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.remote.model.MatchResponseModel
import com.msharibahmed.shaadidotcomassignment.data.remote.model.Result
import com.msharibahmed.shaadidotcomassignment.utils.events.ResponseState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MatchRepository {
    fun getMatchProfilesFromDb(): Flow<List<MatchProfile>> //local data

    suspend fun getMatchesFromRemote(): Flow<ResponseState<List<MatchProfile>>> //remote response data

    suspend fun insertMatchProfiles(matches: List<MatchProfile>) //insert data to local db

    suspend fun changeMatchStatus(matchProfile: MatchProfile) //update match profile status

}
