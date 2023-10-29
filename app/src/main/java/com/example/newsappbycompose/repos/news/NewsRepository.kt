package com.example.newsappbycompose.repos.news

import com.example.newsappbycompose.api.model.ArticlesItem

interface NewsRepository {
    suspend fun getNewsData(sourceId: String): List<ArticlesItem>
}

interface NewsOnlineDataSource {
    suspend fun getNewsFromAPI(sourceId: String): List<ArticlesItem>
}

interface NewsOfflineDataSource {
    suspend fun getNewsFromDB(): List<ArticlesItem>
    suspend fun saveNewsToDB(newsList: List<ArticlesItem>)
}