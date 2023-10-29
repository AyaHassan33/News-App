package com.example.newsappbycompose.repos.news

import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.database.NewsDeo

class NewsOfflineDataSourceImpl(val newsDeo: NewsDeo) : NewsOfflineDataSource {
    override suspend fun getNewsFromDB(): List<ArticlesItem> {
      try {
          return newsDeo.getNewsFromDB()
      }catch (e:Exception){
          throw e
      }
    }

    override suspend fun saveNewsToDB(newsList: List<ArticlesItem>) {
        try {
            return newsDeo.insertNewsToDB(newsList)
        }catch (e:Exception){
            throw e
        }

    }
}