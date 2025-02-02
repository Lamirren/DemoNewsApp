package com.example.demonewsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.demonewsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Tittle",
        description = "Very long Description 123123 123123123 123123123 123123123 123123123 12312312312 123123123",
        image = R.drawable.ic_launcher_foreground
    ),
    Page(
        title = "Tittle",
        description = "Description",
        image = R.drawable.ic_launcher_foreground
    ),
    Page(
        title = "Tittle",
        description = "Description",
        image = R.drawable.ic_launcher_foreground
    ),
)