package com.example.demonewsapp.presentation.details.components

import com.example.demonewsapp.domain.model.Article


sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}