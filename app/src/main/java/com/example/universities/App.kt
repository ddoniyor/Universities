package com.example.universities

import android.app.Application
import android.content.res.Resources

class App:Application() {
    companion object {
        private const val TAG = "Application Class"
        lateinit var instance: Application
        lateinit var resourses: Resources
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        resourses = resources
    }
}