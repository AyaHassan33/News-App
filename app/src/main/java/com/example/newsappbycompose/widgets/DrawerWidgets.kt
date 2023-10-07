package com.example.newsappbycompose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.newsappbycompose.Conts
import com.example.newsappbycompose.R

@Composable
fun DrawerHeader() {
    ConstraintLayout( modifier = Modifier
        .fillMaxWidth(0.6F)
        .fillMaxHeight(0.25F)
        .background(Color(0xFF39A552), shape = RoundedCornerShape(0.dp))
        .padding(vertical = 16.dp)) {
        val (title) =createRefs()
        Text(
            text = stringResource(R.string.news_app),
            modifier = Modifier
                .constrainAs(title){
                                   bottom.linkTo(parent.bottom)
                                   start.linkTo(parent.start)
                                   end.linkTo(parent.end)
                },
            style = TextStyle(
                color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center
            ),
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun DrawerBody(navHostController: NavHostController, onCloseDrawer : ()-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.6F)
            .fillMaxHeight()
            .background(Color.White)
            .padding(top = 16.dp)
    ) {
        NewsDrawerItem(iconId = R.drawable.ic_category, textId = R.string.categories, onNewsDrawerItemClick = {
            navHostController.navigate(Conts.CATEGORIES_ROUTE)
            onCloseDrawer()
           // navHostController.popBackStack(Conts.CATEGORIES_ROUTE,false)
        })
        NewsDrawerItem(iconId = R.drawable.ic_settings, textId = R.string.settings, onNewsDrawerItemClick = {
            navHostController.navigate(Conts.SETTINGS)
            onCloseDrawer()

        })
    }
}

@Composable
fun NewsDrawerItem(iconId: Int, textId: Int , onNewsDrawerItemClick :()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.6F)
            .background(Color.White)
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable {
                onNewsDrawerItemClick()
            }
    ) {
        Icon(painter = painterResource(id = iconId), contentDescription = "")
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(id = textId), style = TextStyle(
                fontSize = 18.sp,
                color = Color(0xFF303030)
            )
        )

    }
}