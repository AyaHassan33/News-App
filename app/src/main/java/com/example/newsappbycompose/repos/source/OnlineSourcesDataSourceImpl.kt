package com.example.newsappbycompose.repos.source

import com.example.newsappbycompose.Constants
import com.example.newsappbycompose.api.NewsServices
import com.example.newsappbycompose.api.model.SourceItem


class OnlineSourcesDataSourceImpl(val newsServices: NewsServices) :OnlineSourcesDataSource {
    override suspend fun getSourcesFromAPI(categoryId: String): List<SourceItem> {
        try {
            return (newsServices.getNewsSources(Constants.API_KEY,categoryId).sources ?: listOf()) as List<SourceItem>
        }catch (e:Exception){
            throw e
        }
    }
}