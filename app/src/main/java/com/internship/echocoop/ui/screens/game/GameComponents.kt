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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internship.echocoop.R
import com.internship.echocoop.ui.components.GameHeaderBox
import com.internship.echocoop.ui.components.GameMenu
import com.internship.echocoop.ui.components.GameText
import com.internship.echocoop.ui.screens.BOTTOM_PADDING
import com.internship.echocoop.ui.screens.TRIANGLE_HEIGHT
import com.internship.echocoop.ui.screens.TRIANGLE_WIDTH

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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
            .padding(top = 65.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GameHeaderBox(modifier = Modifier.size(50.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onPauseClick() },
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

        Spacer(modifier = Modifier.weight(0.15f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                for (i in 1..3) {
                    Image(
                        painter = painterResource(
                            if (i <= lives) R.drawable.ic_heart_red
                            else R.drawable.ic_heart_gray
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(44.dp, 40.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            GameHeaderBox(modifier = Modifier.size(108.dp, 43.dp)) {
                GameText("$score", fontSize = 30.sp)
            }
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
        primaryButtonText = stringResource(id = R.string.exit),
        onPrimaryClick = onExit,
        secondaryButtonText = stringResource(id = R.string.play_again),
        onSecondaryClick = resetGame
    )
}