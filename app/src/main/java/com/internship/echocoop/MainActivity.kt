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
import com.internship.echocoop.ui.navigation.AppNavigation
import com.internship.echocoop.ui.navigation.GameRoute
import com.internship.echocoop.ui.screens.GameScreen
import com.internship.echocoop.ui.navigation.HomeRoute
import com.internship.echocoop.ui.navigation.PrivacyRoute
import com.internship.echocoop.ui.navigation.RecordsRoute
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

@Preview(showBackground = true)
@Composable
fun NavAppPreview() {
    EchoCoopTheme {
        AppNavigation()
    }
}