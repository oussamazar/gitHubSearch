package com.payconiq.githubsearch.presentation.user_search

import com.payconiq.githubsearch.data.remote.dto.SearchUserDto
import com.payconiq.githubsearch.domain.model.SearchUser

data class UserSearchState(
    val isLoading: Boolean = false,
    val search: SearchUser? = null,
    val error: String = ""
)
