package com.internship.echocoop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.internship.echocoop.R
import com.internship.echocoop.data.AppDatabase
import com.internship.echocoop.data.Repository
import com.internship.echocoop.ui.components.GameText
import com.internship.echocoop.ui.components.Header
import com.internship.echocoop.ui.screens.records.RecordsViewModel
import com.internship.echocoop.ui.screens.records.RecordsViewModelFactory
import com.internship.echocoop.ui.theme.EchoCoopTheme
import androidx.compose.foundation.lazy.items
import com.internship.echocoop.ui.screens.records.RecordRow

@Composable
fun RecordsScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    val database = remember { AppDatabase.getDatabase(context) }
    val repository = remember { Repository(database.recordDao()) }
    val viewModel: RecordsViewModel = viewModel (
        factory = RecordsViewModelFactory(repository)
    )

    val allRecords by viewModel.recordsState.collectAsState()
    val isEmpty = allRecords.isEmpty()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(
                id = if (isEmpty) R.drawable.records_background else R.drawable.main_background
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(title = stringResource(id = R.string.records), onBack = onBack)

            Spacer(modifier = Modifier.height(20.dp))

            if (isEmpty) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GameText(text = stringResource(id = R.string.no_records_yet), fontSize = 24.sp)
                }
            } else {
                val topThree = allRecords.take(3)
                val rest = allRecords.takeLast(3)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(topThree) { record ->
                        RecordRow(record, isBgDark = true)
                    }
                    items(rest) { record ->
                        RecordRow(record)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 800)
@Composable
fun RecordsScreenPreview() {
    EchoCoopTheme {
        RecordsScreen(onBack = {})
    }
}