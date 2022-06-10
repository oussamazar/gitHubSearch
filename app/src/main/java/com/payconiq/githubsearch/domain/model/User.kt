package com.payconiq.githubsearch.domain.model

data class User(
    val login: String,
    val name: String?,
    val html_url: String,
    val avatar_url: String,
    val followers: Int,
    val following: Int,
    val public_repos: Int,
)
