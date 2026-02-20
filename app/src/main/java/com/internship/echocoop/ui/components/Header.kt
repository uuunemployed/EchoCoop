package com.internship.echocoop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun Header(title: String, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 65.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            GameHeaderBox(modifier = Modifier.size(50.dp), onClick = onBack) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = "Back",
                    modifier = Modifier.size(width = 33.dp, height = 20.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        GameText(
            text = title,
            fontSize = 40.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 800)
@Composable
fun HeaderPreview() {
    EchoCoopTheme {
        Header (title = "", onBack = {})
    }
}