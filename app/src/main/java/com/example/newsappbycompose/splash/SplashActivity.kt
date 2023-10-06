package com.example.newsappbycompose.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.newsappbycompose.MainActivity
import com.example.newsappbycompose.R
import com.example.newsappbycompose.splash.ui.theme.NewsAppByComposeTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppByComposeTheme {
                // A surface container using the 'background' color from the theme
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    val intent = Intent(this@SplashActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                },2000)
               SplashContent()
            }
        }
    }
}

@Composable
fun SplashContent() {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .paint(painterResource(id = R.drawable.pattern), contentScale = ContentScale.FillBounds))
    {
        val (logo,signature)= createRefs()
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxSize(0.45f)
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                })
        Image(painter = painterResource(id = R.drawable.signature), contentDescription ="signature" ,
            modifier = Modifier
                .fillMaxSize(0.54f)
                .constrainAs(signature) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(logo.bottom)

                })

    }
    /*Column(modifier = Modifier
        .fillMaxSize()
        .paint(
            painter = painterResource(id = R.drawable.pattern),
            contentScale = ContentScale.FillBounds
        ), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "App Logo",
            modifier = Modifier.fillMaxSize(0.45f))
        Image(painter = painterResource(id = R.drawable.signature), contentDescription ="signature" ,
            modifier = Modifier.fillMaxSize(0.5f))
    }*/
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    NewsAppByComposeTheme {
        SplashContent()

    }
}