package com.example.demonewsapp.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.demonewsapp.presentation.bookmark.BookmarkScreen
import com.example.demonewsapp.presentation.bookmark.BookmarkViewModel
import com.example.demonewsapp.presentation.news_navigator.NewsNavigator
import com.example.demonewsapp.presentation.onboarding.OnBoardingScreen
import com.example.demonewsapp.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ) {
            composable(route = Route.NewsNavigationScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                NewsNavigator()
            }
        }
    }
}