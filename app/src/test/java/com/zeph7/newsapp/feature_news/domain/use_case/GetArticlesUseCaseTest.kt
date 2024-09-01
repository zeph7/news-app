package com.zeph7.newsapp.feature_news.domain.use_case

import com.google.gson.Gson
import com.zeph7.newsapp.feature_news.CoroutineTestExtension
import com.zeph7.newsapp.feature_news.data.remote.dto.HeadlineDto
import com.zeph7.newsapp.feature_news.data.repository.NewsRepositoryImpl
import com.zeph7.newsapp.feature_news.fake.NewsApiStub
import com.zeph7.newsapp.feature_news.fake.NewsDaoStub
import com.zeph7.test_utils.util.TestJsonReader
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class GetArticlesUseCaseTest {

    @JvmField
    @RegisterExtension
    val coroutineTestExtension = CoroutineTestExtension()

    private lateinit var getArticlesUseCase: GetArticlesUseCase

    private class NewsApi : NewsApiStub() {
        val jsonReader = TestJsonReader()

        override suspend fun getTopHeadlines(country: String, apiKey: String): HeadlineDto {
            val response = when (apiKey) {
                API_KEY_SUCCESS -> jsonReader.readJsonFile("feature_news/get_headlines_success_response.json")
                API_KEY_ERROR -> jsonReader.readJsonFile("feature_news/get_headlines_error_response.json")
                else -> "throw exception"
            }
            return Gson().fromJson(response, HeadlineDto::class.java)
        }
    }

    private class NewsDao : NewsDaoStub() {}

    @BeforeEach
    fun setUp() {
        val newsRepository = NewsRepositoryImpl(NewsApi(), NewsDao())
        getArticlesUseCase = GetArticlesUseCase(newsRepository, coroutineTestExtension.dispatcher)
    }

    @Test
    fun `getTopHeadlines returns success`() = runTest {
        val response = getArticlesUseCase()

        assertEquals(2, response.data?.size)
    }

    companion object {
        const val API_KEY_SUCCESS = "api_key_success"
        const val API_KEY_ERROR = "api_key_error"
        const val API_KEY_EXCEPTION = "api_key_exception"
    }
}