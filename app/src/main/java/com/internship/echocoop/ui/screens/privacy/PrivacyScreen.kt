package com.internship.echocoop.ui.screens.privacy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.internship.echocoop.R
import com.internship.echocoop.ui.components.Header

@Composable
fun PrivacyScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.privacy_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Header(
            title = stringResource(id = R.string.privacy_policy),
            onBack = onBack,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}