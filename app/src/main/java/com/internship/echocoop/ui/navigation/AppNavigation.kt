package com.internship.echocoop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.internship.echocoop.ui.screens.GameScreen
import com.internship.echocoop.ui.screens.RecordsScreen
import com.internship.echocoop.ui.screens.home.HomeScreen
import com.internship.echocoop.ui.screens.loading.LoadingScreen
import com.internship.echocoop.ui.screens.privacy.PrivacyScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "loading"
    ) {
        composable("loading") {
            LoadingScreen(onFinished = {
                navController.navigateWhenResumed(HomeRoute) {
                    popUpTo("loading") { inclusive = true }
                }
            })
        }

        composable(HomeRoute) {
            HomeScreen(
                onStartClick = { navController.navigateWhenResumed(GameRoute) },
                onRecordsClick = { navController.navigateWhenResumed(RecordsRoute) },
                onPrivacyClick = { navController.navigateWhenResumed(PrivacyRoute) }
            )
        }

        composable(GameRoute) {
            GameScreen(onExit = { navController.navigateUpWhenResumed() })
        }

        composable(RecordsRoute) {
            RecordsScreen(onBack = { navController.navigateUpWhenResumed() })
        }

        composable(PrivacyRoute) {
            PrivacyScreen(onBack = { navController.navigateUpWhenResumed() })
        }
    }
}

private fun NavController.navigateUpWhenResumed() {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        navigateUp()
    }
}

private fun NavController.navigateWhenResumed(route: String) {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        navigate(route)
    }
}