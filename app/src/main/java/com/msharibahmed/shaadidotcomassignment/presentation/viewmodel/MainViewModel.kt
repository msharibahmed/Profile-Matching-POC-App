package com.msharibahmed.shaadidotcomassignment.presentation.viewmodel

import androidx.lifecycle.*
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.local.localMatchRepository.LocalMatchRepository
import com.msharibahmed.shaadidotcomassignment.data.remote.remoteMatchRepository.RemoteMatchRepository
import com.msharibahmed.shaadidotcomassignment.utils.Status
import com.msharibahmed.shaadidotcomassignment.utils.events.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val localMatchRepository: LocalMatchRepository,
    private val remoteMatchRepository: RemoteMatchRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel(), LifecycleObserver {

    private val _responseState: MutableLiveData<ResponseState<List<MatchProfile>>> =
        MutableLiveData()

    val responseState: LiveData<ResponseState<List<MatchProfile>>>
        get() = _responseState

    fun getMatchProfiles() {
        viewModelScope.launch(coroutineDispatcher) {
            remoteMatchRepository.getMatchesFromRemote()
                .onEach {
                    _responseState.value = it
                }.launchIn(viewModelScope)

        }
    }

    fun changeMatchProfileStatus(matchProfile: MatchProfile, status: Status) {
        viewModelScope.launch(coroutineDispatcher) {
            matchProfile.status = status
            localMatchRepository.changeMatchStatus(matchProfile)
        }
    }


}