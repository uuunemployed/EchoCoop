package com.internship.echocoop.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.internship.echocoop.R
import com.internship.echocoop.ui.components.GameBox
import com.internship.echocoop.ui.components.GameText
import com.internship.echocoop.ui.theme.EchoCoopTheme

@Composable
fun HomeScreen(
    onStartClick: () -> Unit,
    onRecordsClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {

    Box {
        Image(
            painter = painterResource(id = R.drawable.main_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(width = 257.dp, height = 273.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(30.dp))

            Column(
                    verticalArrangement = Arrangement.spacedBy(29.dp),
                ) {
                GameMenuButton("START", onStartClick)
                GameMenuButton("RECORDS", onRecordsClick)
                GameMenuButton("PRIVACY POLICY", onPrivacyClick)
            }
        }
    }
}

@Composable
fun GameMenuButton(text: String, onClick: () -> Unit) {
    GameBox (
        bg = "white",
        modifier = Modifier.clickable {
            onClick()
        }
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

@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun HomeScreenPreview() {
    EchoCoopTheme {
        HomeScreen(
            onStartClick = {},
            onRecordsClick = {},
            onPrivacyClick =  {},
            )
    }
}