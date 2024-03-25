package com.zeph7.newsapp.feature_news.data.mapper

import com.zeph7.newsapp.feature_news.data.local.entity.ArticleEntity
import com.zeph7.newsapp.feature_news.data.remote.dto.ArticleDto
import com.zeph7.newsapp.feature_news.domain.model.Article

fun ArticleDto.toArticle(): Article {
    return Article(
        source = source.name,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = hashCode(),
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}