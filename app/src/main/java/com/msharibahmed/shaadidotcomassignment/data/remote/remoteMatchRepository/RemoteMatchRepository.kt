package com.msharibahmed.shaadidotcomassignment.data.remote.remoteMatchRepository

import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.remote.model.MatchResponseModel
import com.msharibahmed.shaadidotcomassignment.data.remote.model.Result
import com.msharibahmed.shaadidotcomassignment.utils.events.ResponseState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteMatchRepository {
    suspend fun getMatchesFromRemote(): Flow<ResponseState<List<MatchProfile>>> //remote response data
}
