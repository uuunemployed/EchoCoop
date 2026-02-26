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
import androidx.compose.ui.graphics.Color
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
    onSecondaryClick: () -> Unit,
    isTopButton: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MenuBgOverlay)
            .clickable(enabled = true, onClick = { })
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.65f))

        Box(modifier = Modifier.fillMaxWidth().height(50.dp)) {
            if (isTopButton) {
                GameHeaderBox(
                    modifier = Modifier.size(50.dp),
                    onClick = onPrimaryClick
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "Exit",
                        modifier = Modifier.size(width = 33.dp, height = 20.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1.9f))

        GameText(text = title, fontSize = 70.sp, uppercase = true)

        Spacer(modifier = Modifier.height(25.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val buttonWidthFraction = 184f / 412f
            
            GameBox(modifier = Modifier.fillMaxWidth(buttonWidthFraction).height(61.dp)) {
                if (primaryButtonText != null) {
                    Button(
                        onClick = onPrimaryClick,
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        elevation = null
                    ) { GameText(primaryButtonText, fontSize = 24.sp) }
                } else {
                    subtitle?.let { GameText(text = it, fontSize = 24.sp) }
                }
            }
            
            GameBox(modifier = Modifier.fillMaxWidth(buttonWidthFraction).height(61.dp)) {
                Button(
                    onClick = onSecondaryClick,
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    elevation = null
                ) { GameText(secondaryButtonText, fontSize = 24.sp) }
            }
        }

        Spacer(modifier = Modifier.weight(3.92f))
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