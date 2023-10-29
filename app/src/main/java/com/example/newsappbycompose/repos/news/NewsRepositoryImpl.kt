package com.example.newsappbycompose.repos.news

import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.repos.NetworkHandler

class NewsRepositoryImpl(
    val onlineDataSource: NewsOnlineDataSource,
    val offlineDataSource: NewsOfflineDataSource,
    val networkHandler: NetworkHandler
) : NewsRepository {
    override suspend fun getNewsData(sourceId: String): List<ArticlesItem> {
        return try {
           if(networkHandler.isOnline()){
               val newsList = onlineDataSource.getNewsFromAPI(sourceId)
               offlineDataSource.saveNewsToDB(newsList)
               newsList
           }else
               offlineDataSource.getNewsFromDB()
       }catch (e:Exception){
           throw e
       }

    }

}