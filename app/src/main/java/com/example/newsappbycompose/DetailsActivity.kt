package com.example.newsappbycompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.api.model.NewsResponse
import com.example.newsappbycompose.ui.theme.NewsAppByComposeTheme

class DetailsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppByComposeTheme {
                // A surface container using the 'background' color from the theme
                val article = intent.getSerializableExtra(Conts.ARTICLES_ITEM,ArticlesItem::class.java)
                ArticlesDetailsContent(articlesItem = article)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticlesDetailsContent(articlesItem: ArticlesItem?) {
    Column {
        GlideImage(model = articlesItem?.urlToImage, contentDescription = "")
        Text(text = articlesItem?.author ?: "")
        Text(text = articlesItem?.title ?: "")
        Text(text = articlesItem?.publishedAt ?: "")
        Text(text = articlesItem?.content ?: "")
        Text(text = articlesItem?.url ?: "")

    }

    
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    NewsAppByComposeTheme {

    }
}