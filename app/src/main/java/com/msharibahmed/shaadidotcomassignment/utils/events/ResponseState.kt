package com.msharibahmed.shaadidotcomassignment.utils.events

sealed class ResponseState<out R> {

    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error<out T>(val exception: Exception, val data: T) : ResponseState<T>()
    object Loading : ResponseState  <Nothing>()
}