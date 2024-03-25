package com.zeph7.newsapp.feature_news.domain.use_case.bookmark

data class BookmarkUseCases(
    val upsertBookmark: UpsertBookmarkUseCase,
    val deleteBookmark: DeleteBookmarkUseCase,
    val getBookmark: GetBookmarkUseCase,
    val getBookmarks: GetBookmarksUseCase,
)
