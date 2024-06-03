package com.techva11.calculatefuelproject.data

import com.techva11.calculatefuelproject.database.HistoryDatabase
import com.techva11.calculatefuelproject.database.HistoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val database: HistoryDatabase) {

    suspend fun insertNew(historyEntity: HistoryEntity) =
        database.historyDao().insert(historyEntity)

    fun getAllHistory(): Flow<List<HistoryEntity>> = database.historyDao().selectAll()

    fun getLast(): Flow<HistoryEntity?> = database.historyDao().selectLast()
}