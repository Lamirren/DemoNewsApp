package com.example.demonewsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demonewsapp.BuildConfig
import com.example.demonewsapp.data.remote.dto.NewsApi
import com.example.demonewsapp.domain.model.Article


class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newResponse = newsApi.getNews(sources = sources, page = page, apiKey = BuildConfig.API_KEY)
            totalNewsCount += newResponse.articles.size
            // API can duplicate some articles so distinct unique
            val articles = newResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}