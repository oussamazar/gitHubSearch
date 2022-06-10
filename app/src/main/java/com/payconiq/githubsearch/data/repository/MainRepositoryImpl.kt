package com.payconiq.githubsearch.data.repository

import com.payconiq.githubsearch.data.remote.GithubSearchHelper
import com.payconiq.githubsearch.data.remote.dto.SearchUserDto
import com.payconiq.githubsearch.data.remote.dto.UserDto
import com.payconiq.githubsearch.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val githubSearchHelper: GithubSearchHelper) :
    MainRepository {


    override suspend fun searchUser(name: String): SearchUserDto {
        return githubSearchHelper.searchUser(name)
    }

    override suspend fun getUserDetail(login: String): UserDto {
        return githubSearchHelper.getUserDetail(login)
    }


}