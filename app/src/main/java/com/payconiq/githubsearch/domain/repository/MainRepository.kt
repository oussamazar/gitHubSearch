package com.payconiq.githubsearch.domain.repository

import com.payconiq.githubsearch.data.remote.dto.SearchUserDto
import com.payconiq.githubsearch.data.remote.dto.UserDto

interface MainRepository {

    suspend fun searchUser(name: String): SearchUserDto

    suspend fun getUserDetail(login: String): UserDto

}