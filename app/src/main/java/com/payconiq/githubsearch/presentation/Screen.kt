package com.payconiq.githubsearch.presentation

sealed class Screen(val route:String){

    object UserResearchScreen: Screen("UserResearchScreen")
    object UserDetailScreen: Screen("UserDetailScreen")

}
