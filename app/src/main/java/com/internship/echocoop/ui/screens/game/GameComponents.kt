package com.internship.echocoop.ui.screens.game

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internship.echocoop.R
import com.internship.echocoop.ui.components.GameHeaderBox
import com.internship.echocoop.ui.components.GameMenu
import com.internship.echocoop.ui.components.GameText
import com.internship.echocoop.ui.theme.EchoCoopTheme
import com.internship.echocoop.ui.theme.White


private const val TRIANGLE_WIDTH = 162f
private const val TRIANGLE_HEIGHT = 140f
private const val BOTTOM_PADDING = 38f

@Composable
fun GameHero(
    rotationAngle: Float,
    onRotate: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = BOTTOM_PADDING.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        val animatedRotation by animateFloatAsState(
            targetValue = rotationAngle,
            animationSpec = spring(stiffness = Spring.StiffnessLow),
            label = "TriangleRotation"
        )
        Box(
            modifier = Modifier
                .size(width = TRIANGLE_WIDTH.dp, height = TRIANGLE_HEIGHT.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onRotate() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.chicken_triangle),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationZ = animatedRotation
                        transformOrigin = TransformOrigin(0.5f, 0.667f)
                    }
            )
        }
    }
}

@Composable
fun GameTopUI(
    lives: Int,
    score: Int,
    onPauseClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(0.65f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameHeaderBox(
                    modifier = Modifier.size(50.dp),
                    onClick = onPauseClick
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(2) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 2.dp)
                                    .size(6.dp, 22.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(White)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(55f / 412f))

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        repeat(3) { i ->
                            Image(
                                painter = painterResource(
                                    id = if (i < lives) R.drawable.ic_heart_red
                                    else R.drawable.ic_heart_gray
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(36f / 412f))

                    GameHeaderBox(
                        modifier = Modifier
                            .width(100.dp)
                            .height(43.dp)
                    ) {
                        GameText(
                            text = "$score",
                            fontSize = 28.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(10f))
        }
    }
}
@Composable
fun PauseMenu(onResume: () -> Unit, onExit: () -> Unit) {
    GameMenu(
        title = stringResource(id = R.string.pause),
        primaryButtonText = stringResource(id = R.string.resume),
        onPrimaryClick = onResume,
        secondaryButtonText = stringResource(id = R.string.exit),
        onSecondaryClick = onExit
    )
}

@Composable
fun GameOverMenu(score: Int, resetGame: () -> Unit, onExit: () -> Unit) {
    GameMenu(
        title = stringResource(id = R.string.game_over),
        subtitle = "Score: $score",
        primaryButtonText = null,
        onPrimaryClick = onExit,
        secondaryButtonText = stringResource(id = R.string.play_again),
        onSecondaryClick = resetGame,
        isTopButton = true
    )
}

@Preview(showBackground = true, widthDp = 412, heightDp = 800)
@Composable
fun GameTopUIPreview() {
    EchoCoopTheme {

            GameTopUI(3, 76) {  }

    }
}