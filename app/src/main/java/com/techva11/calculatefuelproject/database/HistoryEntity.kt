package com.techva11.calculatefuelproject.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var distance: Float,
    var priceGas: Float,
    var countGas: Float,
    var tripPrice : Float,
    var averageLitres : Float,
    var date: String
)
