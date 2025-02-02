package com.example.demonewsapp.domain.usecases.news

import com.example.demonewsapp.data.local.NewsDao
import com.example.demonewsapp.domain.model.Article
import com.example.demonewsapp.domain.repository.NewsRepository

class DeleteArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}