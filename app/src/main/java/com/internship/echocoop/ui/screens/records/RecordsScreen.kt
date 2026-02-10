package com.internship.echocoop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.internship.echocoop.R
import com.internship.echocoop.AppDatabase
import com.internship.echocoop.GameRecord
import com.internship.echocoop.ui.components.GameBox
import com.internship.echocoop.ui.components.GameText
import com.internship.echocoop.ui.components.Header
import com.internship.echocoop.ui.theme.EchoCoopTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RecordsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val allRecords by db.recordDao().getTopRecords().collectAsState(initial = emptyList())

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
            modifier = Modifier.fillMaxSize().statusBarsPadding().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(title = "Records", onBack = onBack)

            Spacer(modifier = Modifier.height(20.dp))

            if (isEmpty) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GameText(text = "No Records Yet", fontSize = 24.sp)
                }
            } else {
                val sdf = remember { SimpleDateFormat("dd.MM", Locale.getDefault()) }
                val topThree = allRecords.take(3)
                val rest = allRecords.drop(3).takeLast(3)

                topThree.forEach { record ->
                    RecordRow(record, sdf, bg = "dark")
                }

                Spacer(modifier = Modifier.height(10.dp))

                rest.forEach { record ->
                    RecordRow(record, sdf, bg = "white")
                }
            }
        }
    }
}

@Composable
fun RecordRow(record: GameRecord, sdf: SimpleDateFormat, bg: String) {
    val dateString = sdf.format(Date(record.date))
    GameBox(bg = bg, width = 314, height = 60) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameText(dateString, fontSize = 18.sp)
            GameText("${record.score}", fontSize = 22.sp)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Preview(showBackground = true, widthDp = 412, heightDp = 800)
@Composable
fun RecordsScreenPreview() {
    EchoCoopTheme {
        RecordsScreen(onBack = {})
    }
}