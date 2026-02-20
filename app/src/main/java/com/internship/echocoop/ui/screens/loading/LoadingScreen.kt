package com.internship.echocoop.ui.screens.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.internship.echocoop.R
import com.internship.echocoop.ui.theme.*

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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1.86f))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.fillMaxWidth(257f/412f),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.weight(1.76f))

            Box(
                modifier = Modifier
                    .fillMaxWidth(199f/412f)
                    .aspectRatio(199f/23f)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(DiamondPink, DiamondDark),
                            center = Offset.Unspecified,
                            radius = 250f
                        )
                    )
                    .border(1.dp, Black, RoundedCornerShape(18.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(18.dp))
                        .background(DiamondPink)
                        .border(2.dp, ProgressBorder, RoundedCornerShape(18.dp))
                )
            }

            Spacer(modifier = Modifier.weight(2.59f))
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