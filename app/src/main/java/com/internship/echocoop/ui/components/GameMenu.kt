package com.internship.echocoop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internship.echocoop.R
import com.internship.echocoop.ui.theme.EchoCoopTheme
import com.internship.echocoop.ui.theme.MenuBgOverlay

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
            .background(MenuBgOverlay)
            .clickable(enabled = true, onClick = {})
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 10.dp)
                .padding(top = 65.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (title == "Game Over") {
                GameHeaderBox(
                    modifier = Modifier.size(50.dp),
                    onClick = onPrimaryClick
                ) {
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

            Column(verticalArrangement = Arrangement.spacedBy(17.dp)) {

                GameBox(
                    modifier = Modifier
                        .width(184.dp)
                        .height(61.dp)
                ) {
                    if (primaryButtonText != null) {
                        Button(
                            onClick = onPrimaryClick,
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = androidx.compose.ui.graphics.Color.Transparent
                            ),
                            elevation = null
                        ) {
                            GameText(primaryButtonText, fontSize = 25.sp)
                        }
                    } else {
                        subtitle?.let { GameText(text = it, fontSize = 25.sp) }
                    }
                }

                GameBox(
                    modifier = Modifier
                        .width(184.dp)
                        .height(61.dp)
                ) {
                    Button(
                        onClick = onSecondaryClick,
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color.Transparent
                        ),
                        elevation = null
                    ) {
                        GameText(secondaryButtonText, fontSize = 25.sp)
                    }
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
            secondaryButtonText = "Play Again",
            onSecondaryClick = { },
        )
    }
}