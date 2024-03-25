package com.zeph7.newsapp.feature_news.di

import com.zeph7.newsapp.feature_news.data.local.NewsDao
import com.zeph7.newsapp.feature_news.data.remote.NewsApi
import com.zeph7.newsapp.feature_news.data.repository.NewsRepositoryImpl
import com.zeph7.newsapp.feature_news.domain.repository.NewsRepository
import com.zeph7.newsapp.feature_news.domain.use_case.GetArticlesUseCase
import com.zeph7.newsapp.feature_news.domain.use_case.SearchNewsUseCase
import com.zeph7.newsapp.feature_news.domain.use_case.bookmark.BookmarkUseCases
import com.zeph7.newsapp.feature_news.domain.use_case.bookmark.DeleteBookmarkUseCase
import com.zeph7.newsapp.feature_news.domain.use_case.bookmark.GetBookmarkUseCase
import com.zeph7.newsapp.feature_news.domain.use_case.bookmark.GetBookmarksUseCase
import com.zeph7.newsapp.feature_news.domain.use_case.bookmark.UpsertBookmarkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi, dao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetArticlesUseCase(repository: NewsRepository): GetArticlesUseCase {
        return GetArticlesUseCase(repository, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(repository: NewsRepository): SearchNewsUseCase {
        return SearchNewsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideBookmarkUseCase(repository: NewsRepository): BookmarkUseCases {
        return BookmarkUseCases(
            upsertBookmark = UpsertBookmarkUseCase(repository),
            deleteBookmark = DeleteBookmarkUseCase(repository),
            getBookmark = GetBookmarkUseCase(repository),
            getBookmarks = GetBookmarksUseCase(repository),
        )
    }

}