package com.msharibahmed.shaadidotcomassignment.data.remote.api

import com.msharibahmed.shaadidotcomassignment.data.remote.model.MatchResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface IApiService {
    @GET("api/?results=10")
    suspend fun getAllMatches(): Response<MatchResponseModel>
}