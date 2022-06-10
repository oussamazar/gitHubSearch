package com.payconiq.githubsearch.data.remote

import com.payconiq.githubsearch.data.remote.dto.SearchUserDto
import com.payconiq.githubsearch.data.remote.dto.UserDto

interface GithubSearchHelper {

    suspend fun searchUser(name: String): SearchUserDto

    suspend fun getUserDetail(login: String): UserDto
}