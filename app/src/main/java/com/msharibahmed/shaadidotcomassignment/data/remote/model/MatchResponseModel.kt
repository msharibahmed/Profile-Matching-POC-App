package com.msharibahmed.shaadidotcomassignment.data.remote.model


import com.google.gson.annotations.SerializedName

data class MatchResponseModel(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)