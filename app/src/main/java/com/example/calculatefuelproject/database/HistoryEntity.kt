package com.example.calculatefuelproject.database

import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var distance: Float,
    var priceGas: Float,
    var countGas: Float
)
