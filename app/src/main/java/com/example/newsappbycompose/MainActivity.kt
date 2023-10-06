package com.example.newsappbycompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsappbycompose.api.APIManager
import com.example.newsappbycompose.api.model.SourceItem
import com.example.newsappbycompose.api.model.SourcesResponse
import com.example.newsappbycompose.ui.theme.NewsAppByComposeTheme
import com.example.newsappbycompose.widgets.CategoriesContent
import com.example.newsappbycompose.widgets.DrawerBody
import com.example.newsappbycompose.widgets.DrawerHeader
import com.example.newsappbycompose.widgets.NewsFragment
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppByComposeTheme {
                // A surface container using the 'background' color from the theme
                var sourcesList : MutableState<List<SourceItem>>  = remember{ mutableStateOf(listOf()) }
              // request
               
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                ModalNavigationDrawer(drawerContent = {
                    Column(modifier = Modifier
                        .fillMaxSize()) {
                        DrawerHeader()
                        DrawerBody()
                    }
                }, drawerState = drawerState) {
                    Scaffold(
                        topBar = { NewsAppBar(drawerState) })
                    {
                      //  NewsSourcesTabs(sourcesItemList = sourcesList.value)
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Conts.CATEGORIES_ROUTE,
                            modifier = Modifier.padding(top = it.calculateTopPadding()))
                        {
                            composable(route= Conts.CATEGORIES_ROUTE){
                                CategoriesContent(navController)
                            }
                            composable(route= "${Conts.NEWS_ROUTE}/{category}",
                                arguments = listOf(navArgument(name = "category") {
                                    type = NavType.StringType
                                })
                            )
                                {
                                    val argument = it.arguments?.getString("category")
                                    NewsFragment(argument)

                            }
                        }
                    }
                }



                    //.execute()

                // enqueue() Run on background Thread
                // execute() Run on Main Thread  ---> Main Tread :- User Interaction / user clicks

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(title = {
        Text(text = stringResource(R.string.news),
            style = TextStyle(color = Color.White),
            modifier = Modifier.padding(14.dp),
            fontSize = 22.sp)

    }, modifier = Modifier
        .clip(shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 28.dp,
            bottomEnd = 28.dp)),
        colors = TopAppBarDefaults
            .centerAlignedTopAppBarColors(containerColor = Color(0xFF39A552),
                navigationIconContentColor = Color.White
            ),
        navigationIcon = {
            IconButton(onClick = { scope.launch{drawerState.open()} }, modifier = Modifier.padding(6.dp)) {
                Icon(painter = painterResource(id = R.drawable.icon_menu), contentDescription = "Icon Navigation")
                
            }
        }

        )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NewsAppByComposeTheme {
        Scaffold() {


        }

    }
}