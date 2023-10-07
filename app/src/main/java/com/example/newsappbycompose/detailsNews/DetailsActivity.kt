package com.example.newsappbycompose.detailsNews

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsappbycompose.Conts
import com.example.newsappbycompose.R
import com.example.newsappbycompose.api.model.ArticlesItem
import com.example.newsappbycompose.ui.theme.NewsAppByComposeTheme
import com.example.newsappbycompose.widgets.news.NewsCard
import com.example.newsappbycompose.widgets.news.NewsViewModel

class DetailsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppByComposeTheme {
                // A surface container using the 'background' color from the theme
                /*val article =
                    intent.getSerializableExtra(Conts.ARTICLES_ITEM, ArticlesItem::class.java)*/
                ArticlesDetailsContent(ArticlesItem())
            }
        }
    }
}
/*@Composable
fun DetailsNews(articlesList: List<ArticlesItem>) {
    val selectedIndex by remember { mutableStateOf(0) }

    LazyColumn {
        item {
            ArticlesDetailsContent(articlesItem = articlesList.get(0))
        }
    }
}*/

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticlesDetailsContent(articlesItem: ArticlesItem) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.pattern), contentScale = ContentScale.FillBounds)
    ) {
        GlideImage(model = articlesItem.urlToImage, contentDescription = "",modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 7.dp), contentScale = ContentScale.FillWidth)

        Text(
            text = articlesItem.author ?: "", style = TextStyle(
                fontSize = 10.sp, color = colorResource(
                    id = R.color.grey
                )
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 14.dp)
        )
        Text(
            text = articlesItem.title ?:"", style = TextStyle(
                fontSize = 14.sp, color = colorResource(
                    id = R.color.black_color
                )
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, start = 14.dp)
        )
        Text(
            text = articlesItem.publishedAt?: "00:00", style = TextStyle(
                fontSize = 13.sp, color = colorResource(
                    id = R.color.grey2
                ), textAlign = TextAlign.End
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, end = 14.dp)
        )
        Text(
            text = articlesItem.content ?:"not found content", style = TextStyle(
                fontSize = 15.sp, color = colorResource(
                    id = R.color.black_color
                )
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        )
        TextButton(
            onClick = {
               /* var intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse(articlesItem?.url)
                context.startActivity(intent)*/
            }, modifier = Modifier
                .align(Alignment.End)
        ) {
            Text(
                text = "View Full Article",
                modifier = Modifier.padding(3.dp),
                style = TextStyle(fontSize = 14.sp, color = colorResource(id = R.color.black_color))
            )
            Image(
                painter = painterResource(id = R.drawable.icon),
                        contentDescription = "",
                modifier = Modifier.padding(6.dp)
            )

        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    NewsAppByComposeTheme {

    }
}