package com.example.demonewsapp.di

import android.app.Application
import android.media.tv.interactive.AppLinkInfo
import androidx.room.Room
import com.example.demonewsapp.data.local.NewsDao
import com.example.demonewsapp.data.local.NewsDatabase
import com.example.demonewsapp.data.local.NewsTypeConvertor
import com.example.demonewsapp.data.manager.LocalUserManagerImplementation
import com.example.demonewsapp.data.remote.dto.NewsApi
import com.example.demonewsapp.data.repository.NewsRepositoryImplementation
import com.example.demonewsapp.domain.manager.LocalUserManager
import com.example.demonewsapp.domain.repository.NewsRepository
import com.example.demonewsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.demonewsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.demonewsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.demonewsapp.domain.usecases.news.DeleteArticle
import com.example.demonewsapp.domain.usecases.news.GetNews
import com.example.demonewsapp.domain.usecases.news.NewsUseCases
import com.example.demonewsapp.domain.usecases.news.SearchNews
import com.example.demonewsapp.domain.usecases.news.SelectArticle
import com.example.demonewsapp.domain.usecases.news.SelectArticles
import com.example.demonewsapp.domain.usecases.news.UpsertArticle
import com.example.demonewsapp.util.Constants.BASE_URL
import com.example.demonewsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImplementation(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImplementation(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository),
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}