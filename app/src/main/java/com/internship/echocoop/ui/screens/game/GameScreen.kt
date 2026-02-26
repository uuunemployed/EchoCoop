package com.internship.echocoop.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.internship.echocoop.R
import com.internship.echocoop.data.AppDatabase
import com.internship.echocoop.data.Repository
import com.internship.echocoop.ui.screens.game.*
import com.internship.echocoop.ui.theme.*

@Composable
fun GameScreen(onExit: () -> Unit) {
    val context = LocalContext.current
    val repository = remember { Repository(AppDatabase.getDatabase(context).recordDao()) }
    val viewModel: GameViewModel = viewModel(factory = GameViewModelFactory(repository))

    LifecycleEventEffect (Lifecycle.Event.ON_PAUSE) {
        viewModel.pauseGame()
    }

    androidx.activity.compose.BackHandler {
        when {
            viewModel.isGameOver -> onExit()
            viewModel.isPaused -> viewModel.togglePause()
            else -> viewModel.togglePause()
        }
    }

    val config = LocalConfiguration.current
    val screenHeightDp = config.screenHeightDp.toFloat()
    val vertexY = screenHeightDp - 38f - 140f

    val gameColors = remember { listOf(StripGreen, StripOrange, StripPink) }

    var currentColorIndex by remember { mutableIntStateOf((0..2).random()) }
    var currentRotation by remember { mutableFloatStateOf(0f) }
    var hasCheckedCollision by remember { mutableStateOf(false) }
    val yPosition = remember { Animatable(-50f) }

    LaunchedEffect(viewModel.isPlaying) {
        if (viewModel.isPlaying) {
            while (viewModel.isPlaying) {
                val target = screenHeightDp + 50f
                val startPos = -50f
                val totalDistance = target - startPos
                val fullDuration = (3000 - (viewModel.score / 5 * 200)).coerceAtLeast(1200)
                val remainingDistance = target - yPosition.value
                val adjustedDuration = ((remainingDistance / totalDistance) * fullDuration).toInt()

                yPosition.animateTo(
                    targetValue = target,
                    animationSpec = tween(
                        durationMillis = if (adjustedDuration > 0) adjustedDuration else 0,
                        easing = LinearEasing
                    )
                )

                if (viewModel.isPlaying) {
                    yPosition.snapTo(startPos)
                    currentColorIndex = (0..2).random()
                    currentRotation = (-20..20).random().toFloat()
                    hasCheckedCollision = false
                }
            }
        } else {
            yPosition.stop()
        }
    }

    LaunchedEffect(yPosition.value) {
        if (!hasCheckedCollision && yPosition.value >= vertexY) {
            hasCheckedCollision = true
            viewModel.handleCollision(currentColorIndex)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.game_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(11.dp)
                .offset(y = yPosition.value.dp)
                .graphicsLayer { rotationZ = currentRotation }
                .background(gameColors[currentColorIndex])
        )

        GameTopUI(viewModel.lives, viewModel.score) { viewModel.togglePause() }
        GameHero(viewModel.rotationAngle) { viewModel.rotate() }

        if (viewModel.isGameOver) {
            GameOverMenu(
                score = viewModel.score,
                resetGame = { viewModel.resetGame() },
                onExit = onExit
            )
        }

        if (viewModel.isPaused) {
            PauseMenu(
                onResume = { viewModel.togglePause() },
                onExit = onExit
            )
        }
    }
}