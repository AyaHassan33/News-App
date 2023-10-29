package com.example.newsappbycompose.repos.source

import com.example.newsappbycompose.api.model.SourceItem
import com.example.newsappbycompose.repos.NetworkHandler

class SourceRepositoryImpl(
    val onlineSourcesDataSource: OnlineSourcesDataSource,
    val offlineSourcesDataSource: OfflineSourcesDataSource,
    val networkHandler: NetworkHandler
) :SourcesRepository {
    override suspend fun getSources(categoryId: String): List<SourceItem> {
        return try {
            if(networkHandler.isOnline()){
                val newsList = onlineSourcesDataSource.getSourcesFromAPI(categoryId)
                offlineSourcesDataSource.saveSourcesIntoDB(newsList)
                newsList
            }else
                offlineSourcesDataSource.getSourcesFromDB()
        }catch (e:Exception){
            throw e
        }
    }
}