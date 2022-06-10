package com.payconiq.githubsearch.presentation.user_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payconiq.githubsearch.common.Constants
import com.payconiq.githubsearch.common.Resource
import com.payconiq.githubsearch.domain.use_case.user_detail.UserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userDetailUseCase: UserDetailUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {


    private val _state = mutableStateOf(UserDetailState())
    val state: State<UserDetailState> = _state

    init {

        savedStateHandle.get<String>(Constants.LOGIN)?.let { login ->
            getUserDetail(login)
        }

    }


    private fun getUserDetail(login: String) {
        userDetailUseCase(login).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserDetailState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = UserDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}