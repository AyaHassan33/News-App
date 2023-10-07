package com.example.newsappbycompose.widgets.news

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.newsappbycompose.Constants
import com.example.newsappbycompose.api.APIManager
import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.api.model.NewsResponse
import com.example.newsappbycompose.api.model.SourceItem
import com.example.newsappbycompose.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel :ViewModel() {
    val sourcesList = mutableStateOf<List<SourceItem>>((listOf()))
    var selectedIndex = mutableStateOf(0)
    val newsList = mutableStateOf<List<ArticlesItem>?>(listOf())


    fun getNewsBySource(sourceItem: SourceItem, newsResponseState: MutableState<List<ArticlesItem>?>) {
        APIManager
            .getNewsServices()
            .getNewsBySource(Constants.API_KEY, sourceItem.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    val newsResponse = response.body()
                    newsResponseState.value = newsResponse?.articles

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }


            })

    }

    fun getNewsSources(category: String?,sourcesList : MutableState<List<SourceItem>>){
        APIManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY,category ?:"")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    val body = response.body()
                    sourcesList.value = (body?.sources ?: listOf()) as List<SourceItem>

                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                }

            })

    }
}