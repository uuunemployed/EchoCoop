package com.internship.echocoop.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class GameRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val score: Int,
    val date: Long = System.currentTimeMillis()
)