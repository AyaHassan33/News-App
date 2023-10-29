package com.example.newsappbycompose.widgets.news

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappbycompose.Constants
import com.example.newsappbycompose.api.APIManager
import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.api.model.NewsResponse
import com.example.newsappbycompose.api.model.SourceItem
import com.example.newsappbycompose.api.model.SourcesResponse
import com.example.newsappbycompose.database.NewsLocalDatabase
import com.example.newsappbycompose.repos.NetworkHandler
import com.example.newsappbycompose.repos.news.NewsOfflineDataSource
import com.example.newsappbycompose.repos.news.NewsOfflineDataSourceImpl
import com.example.newsappbycompose.repos.news.NewsOnlineDataSource
import com.example.newsappbycompose.repos.news.NewsOnlineDataSourceImpl
import com.example.newsappbycompose.repos.news.NewsRepository
import com.example.newsappbycompose.repos.news.NewsRepositoryImpl
import com.example.newsappbycompose.repos.source.OfflineSourcesDataSource
import com.example.newsappbycompose.repos.source.OfflineSourcesDataSourceImpl
import com.example.newsappbycompose.repos.source.OnlineSourcesDataSource
import com.example.newsappbycompose.repos.source.OnlineSourcesDataSourceImpl
import com.example.newsappbycompose.repos.source.SourceRepositoryImpl
import com.example.newsappbycompose.repos.source.SourcesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val sourcesList = mutableStateOf<List<SourceItem>>((listOf()))
    var selectedIndex = mutableStateOf(0)
    val newsList = mutableStateOf<List<ArticlesItem>?>(listOf())
    val list = mutableStateOf<List<ArticlesItem>>((listOf()))
    var sourcesRepository: SourcesRepository? = null
    var onlineDataSource: OnlineSourcesDataSource? = null
    var offlineSourcesDataSource: OfflineSourcesDataSource? = null
    var networkHandler: NetworkHandler? = null

    var newsRepository: NewsRepository? = null
    var newsOnlineDataSource: NewsOnlineDataSource? = null
    var newsOfflineDataSource: NewsOfflineDataSource? = null


    fun getNewsBySource(
        sourceItem: SourceItem,
        newsResponseState: MutableState<List<ArticlesItem>?>
    ) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                networkHandler = object : NetworkHandler {
                    override fun isOnline(): Boolean {
                        return true
                    }

                }
                newsOfflineDataSource =
                    NewsOfflineDataSourceImpl(NewsLocalDatabase.getInstance().getNewsDao())
                newsOnlineDataSource = NewsOnlineDataSourceImpl(APIManager.getNewsServices())
                newsRepository = NewsRepositoryImpl(
                    newsOnlineDataSource!!,
                    newsOfflineDataSource!!,
                    networkHandler = networkHandler!!
                )
                val response = newsRepository?.getNewsData( sourceItem.id ?: "")
                withContext(Dispatchers.Main) {
                    newsResponseState.value = response ?: listOf()
                }

            }

        } catch (ex: Exception) {
            throw ex
        }

        /* .enqueue(object : Callback<NewsResponse> {
             override fun onResponse(
                 call: Call<NewsResponse>,
                 response: Response<NewsResponse>
             ) {
                 val newsResponse = response.body()
                 newsResponseState.value = newsResponse?.articles
                 list.value = newsResponseState.value ?: listOf()

             }

             override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

             }


         })*/

    }

    fun getNewsSources(category: String?, sourcesList: MutableState<List<SourceItem>>) {

        viewModelScope.launch {
            /* networkHandler=object :NetworkHandler{
                 override fun isOnline(): Boolean {
                    return true
                 }

             }
             onlineDataSource = OnlineSourcesDataSourceImpl(APIManager.getNewsServices())
             offlineSourcesDataSource =
                 OfflineSourcesDataSourceImpl(NewsLocalDatabase.getInstance().getSourcesDao())
             sourcesRepository = SourceRepositoryImpl(
                 onlineDataSource!!,
                 offlineSourcesDataSource!!,
                 networkHandler = networkHandler!!
             )*/
            try {
                /*val response = sourcesRepository?.getSources(category ?: "")
                sourcesList.value = (response ?: listOf())*/
                val response = APIManager
                    .getNewsServices()
                    .getNewsSources(Constants.API_KEY, category ?: "")
                sourcesList.value = (response.sources ?: listOf()) as List<SourceItem>
            } catch (ex: Exception) {
                throw ex
            }
        }


        /*
        try {
                val response = APIManager
                    .getNewsServices()
                    .getNewsSources(Constants.API_KEY, category ?: "")
                sourcesList.value = (response.sources ?: listOf()) as List<SourceItem>
            } catch (ex: Exception) {
                throw ex
            }
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

            })*/

    }
}