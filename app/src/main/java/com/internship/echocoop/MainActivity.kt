package com.internship.echocoop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.internship.echocoop.ui.screens.GameRoute
import com.internship.echocoop.ui.screens.GameScreen
import com.internship.echocoop.ui.screens.HomeRoute
import com.internship.echocoop.ui.screens.PrivacyRoute
import com.internship.echocoop.ui.screens.RecordsRoute
import com.internship.echocoop.ui.screens.RecordsScreen
import com.internship.echocoop.ui.screens.home.HomeScreen
import com.internship.echocoop.ui.screens.loading.LoadingScreen
import com.internship.echocoop.ui.screens.privacy.PrivacyScreen
import com.internship.echocoop.ui.theme.EchoCoopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EchoCoopTheme {

                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    AppNavigation()
                }
            }
        }
    }
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "loading"
    ) {
        composable("loading") {
            LoadingScreen (onFinished = {
                navController.navigate(HomeRoute) {
                    popUpTo("loading") { inclusive = true }
                }
            })
        }

        composable(HomeRoute) {
            HomeScreen(
                onStartClick = { navController.navigate(GameRoute) },
                onRecordsClick = { navController.navigate(RecordsRoute) },
                onPrivacyClick = { navController.navigate(PrivacyRoute) }
            )
        }

        composable(GameRoute) {
            GameScreen(onExit = { navController.popBackStack() })
        }

        composable(RecordsRoute) {
            RecordsScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(PrivacyRoute) {
            PrivacyScreen(onBack = { navController.popBackStack() })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NavAppPreview() {
    EchoCoopTheme {
        AppNavigation()
    }
}