package com.internship.echocoop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internship.echocoop.R
import com.internship.echocoop.ui.theme.EchoCoopTheme

@Composable
fun GameMenu(
    title: String,
    subtitle: String? = null,
    primaryButtonText: String? = null,
    onPrimaryClick: () -> Unit,
    secondaryButtonText: String,
    onSecondaryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xD9000000))
            .clickable(enabled = true, onClick = { })
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 10.dp)
                .padding(top = 65.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (title == "Game Over") {
                GameHeaderBox(width = 50, height = 50, onClick = onPrimaryClick) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "Back",
                        modifier = Modifier.size(width = 33.dp, height = 20.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            GameText(text = title, fontSize = 75.sp, uppercase = true)

            Column(verticalArrangement = Arrangement.spacedBy(17.dp),) {
                GameBox(width = 184, height = 61) {
                    if (primaryButtonText != null) {
                        Button(
                            onClick = onPrimaryClick,
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            elevation = null
                        ) { GameText(primaryButtonText, fontSize = 25.sp) }
                    } else {
                        subtitle?.let { GameText(text = it, fontSize = 25.sp) }
                    }
                }

                GameBox(width = 184, height = 61) {
                    Button(
                        onClick = onSecondaryClick,
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        elevation = null
                    ) { GameText(secondaryButtonText, fontSize = 25.sp) }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 800)
@Composable
fun GameMenuPreview() {
    EchoCoopTheme {
        GameMenu(
            title = "Game Over",
            subtitle = "3",
            onPrimaryClick = {},
            secondaryButtonText = "Game Over",
            onSecondaryClick = { },
        )
    }
}
