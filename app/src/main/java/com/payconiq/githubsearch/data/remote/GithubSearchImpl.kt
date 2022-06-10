package com.payconiq.githubsearch.data.remote

import com.payconiq.githubsearch.data.remote.dto.SearchUserDto
import com.payconiq.githubsearch.data.remote.dto.UserDto
import javax.inject.Inject

class GithubSearchImpl @Inject constructor(private val githubSearchApi: GithubSearchApi) :
    GithubSearchHelper {


    override suspend fun searchUser(name: String): SearchUserDto {
        return githubSearchApi.searchUser(name)
    }

    override suspend fun getUserDetail(login: String): UserDto {
        return githubSearchApi.getUserDetail(login)
    }

}