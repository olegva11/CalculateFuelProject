package com.example.calculatefuelproject

import android.content.Context
import com.example.calculatefuelproject.data.Repository
import com.example.calculatefuelproject.database.DatabaseHistory

class AppContainer(context: Context) {
    private val database = DatabaseHistory().getInstance(context)

    val repository by lazy{
        Repository(database)
    }
}