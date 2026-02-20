package com.internship.echocoop.data

import kotlinx.coroutines.flow.Flow

class Repository(private val recordDao: RecordDao) {

    fun getTopRecords(): Flow<List<GameRecord>> = recordDao.getTopRecords()

    suspend fun saveRecord(record: GameRecord) {
        recordDao.insert(record)
    }
}