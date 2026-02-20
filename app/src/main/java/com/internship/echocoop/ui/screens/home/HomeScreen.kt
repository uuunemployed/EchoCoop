package com.internship.echocoop.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                contentDescription = stringResource(id = R.string.logo),
                modifier = Modifier.fillMaxWidth(257f/412f),
                contentScale = ContentScale.FillWidth
            )

            Spacer(Modifier.height(30.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(29.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GameMenuButton(stringResource(id = R.string.start), onStartClick)
                GameMenuButton(stringResource(id = R.string.records).uppercase(), onRecordsClick)
                GameMenuButton(stringResource(id = R.string.privacy_policy).uppercase(), onPrivacyClick)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun HomeScreenPreview() {
    EchoCoopTheme() {
        HomeScreen(
            onStartClick = {},
            onRecordsClick = {},
            onPrivacyClick =  {},
            )
    }
}