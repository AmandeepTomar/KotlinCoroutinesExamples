package com.amandeep.kotlincoroutinesexamples.view.newFeeds.newsservice

import com.amandeep.kotlincoroutinesexamples.view.newFeeds.model.NewsArticle
import retrofit2.http.GET

interface NewsService {

    @GET("news.json")
   suspend fun getNews():List<NewsArticle>
}