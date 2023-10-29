package com.example.newsappbycompose.api

import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.api.model.NewsResponse
import com.example.newsappbycompose.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    //Your API key is: 17585fd1eee8415289af9d44b15dc9b1
    @GET("top-headlines/sources")
    suspend fun getNewsSources(@Query("apiKey") apiKey : String,
                       @Query("category") category: String) :SourcesResponse

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
    ): NewsResponse
    @GET("everything")
    fun getDetailsByNews(
        @Query("apiKey") apiKey: String,
        @Query("articles") articles: String,
    ): Call<ArticlesItem>

}