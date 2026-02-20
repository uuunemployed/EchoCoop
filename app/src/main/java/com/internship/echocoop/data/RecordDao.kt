package com.internship.echocoop.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.internship.echocoop.data.GameRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Upsert
    suspend fun insert(record: GameRecord)

    @Query("SELECT * FROM records ORDER BY score DESC LIMIT 6")
    fun getTopRecords(): Flow<List<GameRecord>>
}