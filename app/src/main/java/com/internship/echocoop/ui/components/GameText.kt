package com.internship.echocoop.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.internship.echocoop.ui.theme.ChewyFontFamily

@Composable
fun GameText(
    text: String,
    fontSize: TextUnit = 25.sp,
    modifier: Modifier = Modifier,
    uppercase: Boolean = false
) {
    val uppercaseText = if(uppercase) text.uppercase() else text
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = uppercaseText,
            fontSize = fontSize,
            fontFamily = ChewyFontFamily,
            style = TextStyle.Default.copy(
                color = Color(0xA1A50041),
                drawStyle = Stroke(
                    width = 12f,
                    join = StrokeJoin.Round
                )
            ),
        )
        Text(
            text = uppercaseText,
            fontSize = fontSize,
            fontFamily = ChewyFontFamily,
            color = Color.White,
            style = TextStyle.Default,
        )
    }
}

