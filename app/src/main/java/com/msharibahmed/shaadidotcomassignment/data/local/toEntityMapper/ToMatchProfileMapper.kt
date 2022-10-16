package com.msharibahmed.shaadidotcomassignment.data.local.toEntityMapper

import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.remote.model.MatchResponseModel
import javax.inject.Inject

class ToEntityMapper @Inject constructor() {
    fun matchRemoteResponseToEntityMapper(matchNetworkResponse: MatchResponseModel?): List<MatchProfile> {

        var matchProfiles: List<MatchProfile>? = null

        matchNetworkResponse?.let { response ->
            matchProfiles = response.results.map {
                MatchProfile(
                    uuid = it.login.uuid,
                    firstName = it.name.first,
                    lastName = it.name.last,
                    imageUrl = it.picture.large,
                    age = it.dob.age,
                    email = it.email,
                    country = it.location.country
                )
            }
        }

        return matchProfiles ?: mutableListOf()
    }
}