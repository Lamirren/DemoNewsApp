package com.example.demonewsapp.domain.usecases.news

import com.example.demonewsapp.data.local.NewsDao
import com.example.demonewsapp.domain.model.Article
import com.example.demonewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}