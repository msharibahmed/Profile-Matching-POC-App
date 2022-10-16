package com.msharibahmed.shaadidotcomassignment.data.local.localMatchRepository

import com.msharibahmed.shaadidotcomassignment.data.local.dao.MatchDao
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMatchRepositoryImpl @Inject constructor(
    private val matchDao: MatchDao
) : LocalMatchRepository {

    override fun getMatchProfilesFromDb(): Flow<List<MatchProfile>> = matchDao.getAll()


    override suspend fun insertMatchProfiles(matches: List<MatchProfile>) =
        matchDao.insertAll(matches)


    override suspend fun changeMatchStatus(matchProfile: MatchProfile) =
        matchDao.update(uuid = matchProfile.uuid, status = matchProfile.status)

}