package com.example.newsappbycompose

import android.app.Application
import com.example.newsappbycompose.database.NewsLocalDatabase

class NewsApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        NewsLocalDatabase.init(this)
    }
}