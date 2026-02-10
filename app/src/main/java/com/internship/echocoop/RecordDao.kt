package com.internship.echocoop

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Upsert
    suspend fun insert(record: GameRecord)

    @Query("SELECT * FROM records ORDER BY score DESC LIMIT 10")
    fun getTopRecords(): Flow<List<GameRecord>>
}