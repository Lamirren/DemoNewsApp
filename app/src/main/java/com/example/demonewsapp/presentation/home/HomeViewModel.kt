package com.example.demonewsapp.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.demonewsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    val news = newsUseCases.getNews(
        // some sources from documentation
        sources = listOf("bbc-news", "abc-news")
    ).cachedIn(viewModelScope)
}