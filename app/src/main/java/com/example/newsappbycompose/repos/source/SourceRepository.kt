package com.example.newsappbycompose.repos.source

import com.example.newsappbycompose.api.model.SourceItem


interface SourcesRepository {
    suspend fun getSources(categoryId: String): List<SourceItem>
}

interface OnlineSourcesDataSource {
    suspend fun getSourcesFromAPI(categoryId: String): List<SourceItem>
}

interface OfflineSourcesDataSource {
    suspend fun getSourcesFromDB(): List<SourceItem>
    suspend fun saveSourcesIntoDB(list: List<SourceItem>)
}