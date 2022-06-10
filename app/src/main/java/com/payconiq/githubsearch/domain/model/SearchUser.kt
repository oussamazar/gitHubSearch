package com.payconiq.githubsearch.domain.model


data class SearchUser(
    val incomplete_results: Boolean,
    val items: List<User>,
    val total_count: Int
)
