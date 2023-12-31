package com.example.newsappbycompose.detailsNews

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappbycompose.Constants
import com.example.newsappbycompose.api.APIManager
import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.api.model.NewsResponse
import com.example.newsappbycompose.api.model.SourceItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class detailsViewModel : ViewModel() {
    val newsListDetails = mutableStateOf<List<ArticlesItem>?>(listOf())

    fun getNewsBySource(sourceItem: SourceItem, newsResponseState: MutableState<List<ArticlesItem>?>) {
       try {
           viewModelScope.launch {
              val response = APIManager
                   .getNewsServices()
                   .getNewsBySource(Constants.API_KEY, sourceItem.id ?: "")
               newsResponseState.value= response.articles?: listOf()
           }
       } catch (e:Exception){
           throw e
       }
            /*.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    val newsResponse = response.body()
                    newsResponseState.value = newsResponse?.articles


                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }


            })*/

    }
}