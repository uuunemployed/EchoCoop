package com.internship.echocoop.ui.screens.records

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internship.echocoop.data.GameRecord
import com.internship.echocoop.ui.components.GameBox
import com.internship.echocoop.ui.components.GameText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RecordRow(record: GameRecord, bg: String) {
    GameBox(modifier = Modifier.fillMaxWidth().height(60.dp), bg = bg,) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameText(record.date.toDateString(), fontSize = 18.sp)
            GameText("${record.score}", fontSize = 22.sp)
        }
    }
}

private fun Long.toDateString(): String {
    val sdf = SimpleDateFormat("dd.MM", Locale.getDefault())
    return sdf.format(Date(this))
}