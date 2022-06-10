package com.payconiq.githubsearch.data.remote

import com.payconiq.githubsearch.data.remote.dto.SearchUserDto
import com.payconiq.githubsearch.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubSearchApi {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") name: String
    ): SearchUserDto


    @GET("users/{login}")
    suspend fun getUserDetail(@Path("login") login: String): UserDto
}