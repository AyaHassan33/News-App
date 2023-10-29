package com.example.newsappbycompose.repos.news

import com.example.newsappbycompose.Constants
import com.example.newsappbycompose.api.NewsServices
import com.example.newsappbycompose.api.model.ArticlesItem

class NewsOnlineDataSourceImpl(val newsServices: NewsServices) : NewsOnlineDataSource {
    override suspend fun getNewsFromAPI(sourceId: String): List<ArticlesItem> {
       try {
           return newsServices.getNewsBySource(Constants.API_KEY,sourceId).articles ?: listOf()
       }catch (ex:Exception){
           throw ex
       }
    }
}