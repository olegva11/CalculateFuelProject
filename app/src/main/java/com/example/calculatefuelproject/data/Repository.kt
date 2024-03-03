package com.example.calculatefuelproject.data

import com.example.calculatefuelproject.database.HistoryDatabase
import com.example.calculatefuelproject.database.HistoryEntity
import kotlinx.coroutines.flow.Flow

class Repository(private val database: HistoryDatabase) {

    suspend fun insertNew(historyEntity: HistoryEntity) =
        database.historyDao().insert(historyEntity)

    fun getAllHistory(): Flow<List<HistoryEntity>> = database.historyDao().selectAll()

    fun getLast(): Flow<HistoryEntity?> = database.historyDao().selectLast()
}