package com.example.calculatefuelproject.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao() : HistoryDAO
}

open class DatabaseHistory {
    private var mInstance: HistoryDatabase? = null

    @Synchronized
    open fun getInstance(context: Context): HistoryDatabase {

        Log.i("getInstance",context.toString())
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(
                context,
                HistoryDatabase::class.java,
                "history.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        return mInstance as HistoryDatabase
    }
}