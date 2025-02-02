package com.example.demonewsapp.data.remote.dto

import com.example.demonewsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
)
