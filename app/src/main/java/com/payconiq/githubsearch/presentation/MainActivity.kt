package com.payconiq.githubsearch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.payconiq.githubsearch.common.Constants
import com.payconiq.githubsearch.presentation.ui.theme.GithubsearchTheme
import com.payconiq.githubsearch.presentation.user_detail.UserDetailScreen
import com.payconiq.githubsearch.presentation.user_search.UserResearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubsearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.UserResearchScreen.route
                    ) {

                        composable(
                            route = Screen.UserResearchScreen.route
                        ) {
                            UserResearchScreen(navController)
                        }

                        composable(
                            route = Screen.UserDetailScreen.route + "/{login}"
                        ) {
                            UserDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
