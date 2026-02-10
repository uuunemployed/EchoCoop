package com.internship.echocoop.ui.screens

import android.app.Application
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.internship.echocoop.AppDatabase
import com.internship.echocoop.GameRecord
import com.internship.echocoop.R
import com.internship.echocoop.ui.components.GameHeaderBox
import com.internship.echocoop.ui.components.GameMenu
import com.internship.echocoop.ui.components.GameText
import com.internship.echocoop.ui.theme.EchoCoopTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TRIANGLE_WIDTH = 162f
const val TRIANGLE_HEIGHT = 140f
const val BOTTOM_PADDING = 38f
const val STRIP_HEIGHT = 11f

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val recordDao = db.recordDao()

    var score by mutableStateOf(0)
    var lives by mutableStateOf(3)
    var rotationAngle by mutableStateOf(0f)
    var isGameOver by mutableStateOf(false)
    var isPaused by mutableStateOf(false)

    val isPlaying: Boolean get() = !isGameOver && !isPaused

    fun togglePause() {
        if (!isGameOver) isPaused = !isPaused
    }

    fun rotate() {
        if (isPlaying) rotationAngle += 120f
    }

    fun handleCollision(isSuccess: Boolean) {
        if (isSuccess) {
            score++
        } else {
            lives--
            if (lives <= 0) {
                isGameOver = true
                saveFinalScore()
            }
        }
    }

    private fun saveFinalScore() {
        if (score > 0) {
            viewModelScope.launch(Dispatchers.IO) {
                recordDao.insert(GameRecord(score = score))
            }
        }
    }

    fun resetGame() {
        score = 0
        lives = 3
        isGameOver = false
        isPaused = false
        rotationAngle = 0f
    }
}

class GameViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(application) as T
    }
}

@Composable
fun GameScreen(onExit: () -> Unit) {
    val context = LocalContext.current.applicationContext as Application
    val viewModel: GameViewModel = viewModel(factory = GameViewModelFactory(context))
    val config = LocalConfiguration.current
    val screenHeightDp = config.screenHeightDp.toFloat()
    val vertexY = screenHeightDp - BOTTOM_PADDING - TRIANGLE_HEIGHT
    val gameColors = listOf(Color(0xFF4CAF50), Color(0xFFFF9800), Color(0xFFE91E63))
    var currentColorIndex by remember { mutableIntStateOf(0) }
    var hasCheckedCollision by remember { mutableStateOf(false) }
    val yPosition = remember { Animatable(-50f) }
    var currentRotation by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(viewModel.isGameOver) {
        if (!viewModel.isGameOver) {
            yPosition.snapTo(-50f)
        }
    }

    LaunchedEffect(viewModel.isPlaying) {
        if (viewModel.isPlaying) {
            while (viewModel.isPlaying) {
                yPosition.animateTo(
                    targetValue = screenHeightDp + 50f,
                    animationSpec = tween(
                        durationMillis = (3000 * (1f - (yPosition.value + 50f) / (screenHeightDp + 100f))).toInt().coerceAtLeast(0),
                        easing = LinearEasing
                    )
                )
                if (yPosition.value >= screenHeightDp + 50f) {
                    yPosition.snapTo(-50f)
                    hasCheckedCollision = false
                    currentColorIndex = (0..2).random()
                    currentRotation = (-20..20).random().toFloat()
                }
            }
        } else {
            yPosition.stop()
        }
    }

    LaunchedEffect(yPosition.value) {
        if (!hasCheckedCollision && yPosition.value - 50 >= vertexY) {
            hasCheckedCollision = true
            val normalizedAngle = ((viewModel.rotationAngle.toInt() % 360 + 360) % 360)
            val currentTopCorner = normalizedAngle / 120

            viewModel.handleCollision(currentColorIndex == currentTopCorner)
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
                .fillMaxWidth(4.2f)
                .height(STRIP_HEIGHT.dp)
                .offset(y = yPosition.value.dp)
                .graphicsLayer {
                    rotationZ = currentRotation
                }
                .background(gameColors[currentColorIndex])
        )

        GameTopUI(viewModel)

        GameHero(viewModel)

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

@Composable
fun GameHero(viewModel: GameViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = BOTTOM_PADDING.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        val animatedRotation by animateFloatAsState(
            targetValue = viewModel.rotationAngle,
            animationSpec = spring(stiffness = Spring.StiffnessLow), label = ""
        )
        Box(
            modifier = Modifier
                .size(width = TRIANGLE_WIDTH.dp, height = TRIANGLE_HEIGHT.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { viewModel.rotate() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.chicken_triangle),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().graphicsLayer {
                    rotationZ = animatedRotation
                    transformOrigin = TransformOrigin(0.5f, 0.667f)
                }
            )
        }
    }
}

@Composable
fun GameTopUI(viewModel: GameViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
            .padding(top = 65.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GameHeaderBox(width = 50, height = 50) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { viewModel.togglePause() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(2) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(6.dp, 22.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color.White)
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                for (i in 1..3) {
                    Image(
                        painter = painterResource(
                            if (i <= viewModel.lives) R.drawable.ic_heart_red
                            else R.drawable.ic_heart_gray
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(44.dp, 40.dp)
                    )
                }
            }
            GameHeaderBox(width = 108, height = 43) {
                GameText("${viewModel.score}", fontSize = 30.sp)
            }
        }
    }
}

@Composable
fun PauseMenu(onResume: () -> Unit, onExit: () -> Unit) {
    GameMenu (
        title = "Pause",
        primaryButtonText = "Resume",
        onPrimaryClick = { onResume() },
        secondaryButtonText = "Exit",
        onSecondaryClick = { onExit() }
    )
}

@Composable
fun GameOverMenu(score: Int, resetGame: () -> Unit, onExit: () -> Unit) {
    GameMenu(
        title = "Game Over",
        subtitle = "Score: $score",
        primaryButtonText = "Exit",
        onPrimaryClick = { onExit() },
        secondaryButtonText = "Play Again",
        onSecondaryClick = { resetGame() }
    )
}


@Preview(showBackground = true, widthDp = 412, heightDp = 800)
@Composable
fun GameScreenPreview() {
    EchoCoopTheme {
        GameScreen(onExit = {})
    }
}