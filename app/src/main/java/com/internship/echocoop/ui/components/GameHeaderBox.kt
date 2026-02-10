package com.internship.echocoop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.internship.echocoop.ui.theme.EchoCoopTheme

@Composable
fun GameHeaderBox(
    width: Int,
    height: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(width.dp, height.dp)
            .clip(RoundedCornerShape(13.dp))
            .then(
                if (onClick != null) Modifier.clickable { onClick() }
                else Modifier
            )
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF7F4455), Color(0xFFE57CA5)),
                    radius = 50f
                )
            )
            .border(3.dp, Color.White, shape = RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview(showBackground = true, widthDp = 150, heightDp = 50)
@Composable
fun GameScreenPreview() {
    EchoCoopTheme() {
        GameHeaderBox(
            width = 24,
            height = 24,
            modifier  = Modifier,
            onClick = {},
            content = {}
        )
    }
}