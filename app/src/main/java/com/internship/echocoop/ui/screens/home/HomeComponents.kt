package com.internship.echocoop.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.internship.echocoop.R
import com.internship.echocoop.ui.components.GameBox
import com.internship.echocoop.ui.components.GameText

@Composable
fun GameMenuButton(text: String, onClick: () -> Unit) {
    GameBox (
        bg = "white",
        modifier = Modifier
            .size(width = 184.dp, height = 61.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.button_bg_white),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        GameText(text = text)
    }
}