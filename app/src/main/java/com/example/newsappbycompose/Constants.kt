package com.example.newsappbycompose

import com.example.newsappbycompose.api.model.Category

object Constants {
    val API_KEY = "17585fd1eee8415289af9d44b15dc9b1"
        val categories = listOf(
            Category(
                "sports", R.drawable.ball,
                R.string.sports, R.color.red
            ),
            Category(
                "technology", R.drawable.politics,
                R.string.technology, R.color.blue
            ),
            Category(
                "health", R.drawable.health,
                R.string.health, R.color.pink
            ),
            Category(
                "business", R.drawable.bussines,
                R.string.business, R.color.brown_orange
            ),
            Category(
                "general", R.drawable.environment,
                R.string.general, R.color.baby_blue
            ),
            Category(
                "science", R.drawable.science,
                R.string.science, R.color.yellow
            ),
        )

}