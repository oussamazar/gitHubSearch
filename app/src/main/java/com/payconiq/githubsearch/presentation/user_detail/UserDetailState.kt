package com.payconiq.githubsearch.presentation.user_detail

import com.payconiq.githubsearch.data.remote.dto.UserDto

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: UserDto? = null,
    val error: String = ""
)