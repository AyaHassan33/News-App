package com.example.newsappbycompose.widgets

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsappbycompose.Constants
import com.example.newsappbycompose.Conts
import com.example.newsappbycompose.DetailsActivity
import com.example.newsappbycompose.R
import com.example.newsappbycompose.api.APIManager
import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.api.model.Category
import com.example.newsappbycompose.api.model.NewsResponse
import com.example.newsappbycompose.api.model.SourceItem
import com.example.newsappbycompose.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsFragment(category: String?) {
    val sourcesList = remember {
        mutableStateOf<List<SourceItem>>((listOf()))
    }
    val newsList = remember {
        mutableStateOf<List<ArticlesItem>?>(listOf())
    }

    getNewsSources(category,sourcesList)
    Column(modifier = Modifier
        .padding(top = 6.dp, bottom = 9.dp)
        .paint(
            painter = painterResource(id = R.drawable.pattern),
            contentScale = ContentScale.FillBounds
        ))
    {
        NewsSourcesTabs(sourcesList.value,newsList)
        NewsList(articlesList = newsList.value ?: listOf())
    }
}
@Composable
fun NewsList(articlesList: List<ArticlesItem>) {
    LazyColumn {
        items(articlesList.size) {
            NewsCard(articlesItem = articlesList.get(it))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(articlesItem: ArticlesItem) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 12.dp)
        .clickable { 
                val intent= Intent(context,DetailsActivity::class.java)
            intent.putExtra(Conts.ARTICLES_ITEM,articlesItem)
            context.startActivity(intent)

            }
    ){
        GlideImage(
            model = articlesItem.urlToImage ?: "",
            contentDescription = "News Picture",
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
        Text(
            text = articlesItem.author ?: "",
            style = TextStyle(color = colorResource(id = R.color.grey))
        )
        Text(
            text = articlesItem.title ?: "",
            style = TextStyle(colorResource(id = R.color.colorBlack))
        )
        Text(
            text = articlesItem.publishedAt ?: "",

            modifier = Modifier.align(Alignment.End),
            style = TextStyle(
                color = colorResource(id = R.color.grey2),
            )
        )

    }

}

@Composable
fun NewsSourcesTabs(
    sourcesItemList : List<SourceItem>,
    newsResponseState: MutableState<List<ArticlesItem>?>,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember{
        mutableStateOf(0)
    }
    if (sourcesItemList.isNotEmpty())
        ScrollableTabRow(selectedTabIndex = selectedIndex , containerColor = Color.Transparent ,
            modifier = Modifier.fillMaxWidth(), edgePadding = 5.dp, divider = {}, indicator = {}) {
            sourcesItemList.forEachIndexed { index, sourceItem ->
                if (selectedIndex == index) {
                    getNewsBySource(sourceItem, newsResponseState = newsResponseState)
                }
                Tab(selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                    },
                    selectedContentColor = Color.White ,
                    unselectedContentColor = Color(0xFF39A552) ,
                    modifier = if(selectedIndex==index) Modifier
                        .padding(end = 2.dp)
                        .background(
                            Color(0xFF39A552),
                            RoundedCornerShape(50)
                        )
                    else
                        Modifier
                            .padding(end = 2.dp)
                            .border(2.dp, Color(0xFF39A552), RoundedCornerShape(50)),
                    text = { Text(text = sourceItem.name?:"", style = TextStyle(fontSize = 14.sp))}

                )

            }

        }
}

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

fun getNewsSources(category: String?,sourcesList :MutableState<List<SourceItem>>){
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
@Preview(name = "News Card", showSystemUi = true)
@Composable
fun NewsCardPreview() {
    NewsCard(
        articlesItem = ArticlesItem(
            "10 / 9 / 2023",
            "BBC News",
            "URL To Image",
            LoremIpsum(15).toString(), title = "Title ", content = LoremIpsum(20).toString()

        )
    )
}