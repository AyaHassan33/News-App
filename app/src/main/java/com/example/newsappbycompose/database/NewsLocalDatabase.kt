package com.example.newsappbycompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.api.model.SourceItem

@Database(entities = [ArticlesItem::class,SourceItem::class], version = 1)
abstract class NewsLocalDatabase : RoomDatabase() {

    abstract fun getSourcesDao(): SourcesDao
    abstract fun getNewsDao(): NewsDeo

    companion object {
        private var DATABASE: NewsLocalDatabase? = null
        fun init(context: Context) {
            if (DATABASE == null)
                DATABASE =
                    Room.databaseBuilder(
                        context.applicationContext,
                        NewsLocalDatabase::class.java,
                        "News Local DB"
                    ).fallbackToDestructiveMigration()
                        .build()
        }

        fun getInstance(): NewsLocalDatabase {
            return DATABASE!!
        }

    }
}