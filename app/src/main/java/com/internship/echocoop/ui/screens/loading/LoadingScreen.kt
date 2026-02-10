package com.internship.echocoop.ui.screens.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.internship.echocoop.R
import com.internship.echocoop.ui.theme.EchoCoopTheme

@Composable
fun LoadingScreen(onFinished: () -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
        ) { value, _ ->
            progress = value
        }
        onFinished()
    }

    Box {
        Image(
            painter = painterResource(id = R.drawable.main_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(width = 257.dp, height = 273.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(176.dp))

            Box(
                modifier = Modifier
                    .width(176.dp)
                    .height(23.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.DarkGray)
                    .border(1.dp, Color.Black, RoundedCornerShape(18.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFFF67697))
                        .border(2.dp, Color(0xFF3B4E00).copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun LoadingScreenPreview() {
    EchoCoopTheme {
        LoadingScreen(
            onFinished = {}
        )
    }
}