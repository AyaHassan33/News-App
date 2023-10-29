package com.example.newsappbycompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsappbycompose.api.model.ArticlesItem
@Dao
interface NewsDeo {
    @Insert
    suspend fun insertNewsToDB(list: List<ArticlesItem>)

    @Query("SELECT * FROM ArticlesItem")
    suspend fun getNewsFromDB(): List<ArticlesItem>
}