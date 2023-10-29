package com.example.newsappbycompose.repos.source

import com.example.newsappbycompose.api.model.SourceItem
import com.example.newsappbycompose.database.SourcesDao


class OfflineSourcesDataSourceImpl(val sourcesDeo: SourcesDao): OfflineSourcesDataSource {
    override suspend fun getSourcesFromDB(): List<SourceItem> {
        try {
            return sourcesDeo.getSourcesFromDB()
        }catch (ex:Exception){
            throw ex
        }
    }

    override suspend fun saveSourcesIntoDB(list: List<SourceItem>) {
        try {
            return sourcesDeo.insertSourceToDB(list)
        }catch (ex:Exception){
            throw ex
        }
    }
}