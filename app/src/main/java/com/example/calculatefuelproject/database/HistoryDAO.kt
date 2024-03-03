package com.example.calculatefuelproject.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {

    @Insert
    suspend fun insert(pos: HistoryEntity)

    @Delete
    suspend fun delete(pos: HistoryEntity)

    @Query("Select * From history_table")
     fun selectAll(): Flow<List<HistoryEntity>>

    @Query("Select * From history_table Where id = (Select Max(id) from history_table  LIMIT 1)")
     fun selectLast(): Flow<HistoryEntity?>
}