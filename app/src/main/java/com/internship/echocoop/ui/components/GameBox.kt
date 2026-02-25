package com.internship.echocoop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.internship.echocoop.R

@Composable
fun GameBox(
    modifier: Modifier = Modifier,
    isBgDark: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val imageResource = if (isBgDark) {
            R.drawable.button_bg_dark
        } else {
            R.drawable.button_bg_white
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        content()
    }
}