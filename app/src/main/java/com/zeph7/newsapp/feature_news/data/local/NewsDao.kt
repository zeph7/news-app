package com.zeph7.newsapp.feature_news.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zeph7.newsapp.feature_news.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBookmarkArticle(article: ArticleEntity)

    @Delete
    suspend fun deleteBookmarkArticle(article: ArticleEntity)

    @Query("SELECT * FROM ArticleEntity WHERE id = :id")
    suspend fun getBookmarkArticle(id: Int): ArticleEntity?

    @Query("SELECT * FROM ArticleEntity")
    fun getBookmarkArticles(): Flow<List<ArticleEntity>>

}