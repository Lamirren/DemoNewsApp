package com.example.demonewsapp.presentation.bookmark

import com.example.demonewsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
