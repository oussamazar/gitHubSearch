package com.payconiq.githubsearch.data.remote.dto

import com.payconiq.githubsearch.domain.model.SearchUser

data class SearchUserDto(
    val incomplete_results: Boolean,
    val items: List<UserDto>,
    val total_count: Int
)


fun SearchUserDto.toSearchUser(): SearchUser {
    return SearchUser(
        incomplete_results = incomplete_results,
        items = items.map { it.toUser() },
        total_count = total_count
    )
}