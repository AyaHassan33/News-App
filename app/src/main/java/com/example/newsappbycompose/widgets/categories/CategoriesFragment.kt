package com.example.newsappbycompose.widgets.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsappbycompose.Constants
import com.example.newsappbycompose.Conts
import com.example.newsappbycompose.R
import com.example.newsappbycompose.api.model.Category

@Composable
fun CategoriesContent(navHostController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .paint(painter = painterResource(id = R.drawable.pattern), contentScale = ContentScale.FillBounds)
        .padding(19.dp)) {
        Text(text = "Pick your category \n" +
                "of interest", modifier = Modifier.padding(16.dp),
            style = TextStyle(color = Color(0XFF4F5A69), fontSize = 22.sp)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2) ){
            items(6){
                val item = Constants.categories.get(it)
                CategoryCard(item = item, position = it, navHostController =navHostController )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(item : Category,position :Int ,navHostController: NavHostController
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = item.backgroundColor)),
        modifier = Modifier.padding(horizontal = 13.dp, vertical = 4.dp),
        onClick = {
                  navHostController.navigate("${Conts.NEWS_ROUTE}/${item.apiID}")
          //  navHostController.popBackStack()
        },
        shape = if (position % 2 == 0) RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 0.dp
        ) else
            RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomEnd = 16.dp,
                bottomStart = 0.dp
            )
    ) {
        Image(painter = painterResource(id = item.drawableResId), contentDescription = "${item.titleResID}",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 15.dp)
                .height(80.dp),
            contentScale = ContentScale.FillHeight )
        Text(text = stringResource(id = item.titleResID),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 9.dp),
            style = TextStyle(color = Color.White), fontSize = 22.sp
        )
    }
    
}