package com.amandeep.kotlincoroutinesexamples.view.newFeeds

import com.amandeep.kotlincoroutinesexamples.view.newFeeds.model.NewsArticle
import com.amandeep.kotlincoroutinesexamples.view.newFeeds.newsservice.NewsService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {
    companion object{
        private val BASE_URL="https://raw.githubusercontent.com/DevTides/NewsApi/master/"
        private const val NEWS_DELAY=2000L


    }

    private val newsService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)

    fun getNewsArticles():Flow<NewsArticle>{
        return flow<NewsArticle> {
            newsService.getNews().forEach {
                emit(it)
                delay(NEWS_DELAY)
            }
        }
    }
}