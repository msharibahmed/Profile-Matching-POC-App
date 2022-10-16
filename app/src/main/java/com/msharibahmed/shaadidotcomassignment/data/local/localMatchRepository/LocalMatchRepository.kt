package com.msharibahmed.shaadidotcomassignment.data.local.localMatchRepository

import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import kotlinx.coroutines.flow.Flow

interface LocalMatchRepository {
    fun getMatchProfilesFromDb(): Flow<List<MatchProfile>> //local data

    suspend fun insertMatchProfiles(matches: List<MatchProfile>) //insert data to local db

    suspend fun changeMatchStatus(matchProfile: MatchProfile) //update match profile status

}