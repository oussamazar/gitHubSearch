package com.payconiq.githubsearch.presentation.user_search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payconiq.githubsearch.common.Constants
import com.payconiq.githubsearch.common.Resource
import com.payconiq.githubsearch.domain.use_case.user_search.UserSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val userSearchUseCase: UserSearchUseCase
) :
    ViewModel() {

    private val _state = mutableStateOf(UserSearchState())
    val state: State<UserSearchState> = _state

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery



    private var searchJob: Job? = null


    fun userSearch(name: String) {

        _searchQuery.value = name
        searchJob?.cancel()
        if (name.isNotBlank()) {
            searchJob = viewModelScope.launch {
                delay(500L)

                userSearchUseCase(name).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = UserSearchState(search = result.data)
                        }
                        is Resource.Error -> {
                            _state.value = UserSearchState(
                                error = result.message ?: "An unexpected error occured"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = UserSearchState(isLoading = true)
                        }
                    }

                }.launchIn(viewModelScope)

            }
        }

    }
}