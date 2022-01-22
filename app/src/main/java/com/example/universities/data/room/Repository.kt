package com.example.universities.data.room

import android.content.Context

class Repository {
    companion object {
        var appDatabase: AppDatabase? = null

        private fun initDb(context: Context): AppDatabase {
            return AppDatabase.getInstance(context)
        }


    }
}