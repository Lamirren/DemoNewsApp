package com.example.demonewsapp.domain.usecases.news

import com.example.demonewsapp.data.local.NewsDao
import com.example.demonewsapp.domain.model.Article
import com.example.demonewsapp.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}