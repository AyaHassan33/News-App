package com.example.newsappbycompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsappbycompose.api.model.SourceItem


@Dao
interface SourcesDao {
    @Insert
    suspend fun insertSourceToDB(list: List<SourceItem>)

    @Query("SELECT * FROM SourceItem")
    suspend fun getSourcesFromDB(): List<SourceItem>
}