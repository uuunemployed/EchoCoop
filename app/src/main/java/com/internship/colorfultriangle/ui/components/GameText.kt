package com.internship.colorfultriangle.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.internship.colorfultriangle.ui.theme.ChewyFontFamily
import com.internship.colorfultriangle.ui.theme.GameTextOutline
import com.internship.colorfultriangle.ui.theme.White

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
                color = GameTextOutline,
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
            color = White,
            style = TextStyle.Default,
        )
    }
}

