package com.amandeep.kotlincoroutinesexamples.view.newFeeds.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amandeep.kotlincoroutinesexamples.view.newFeeds.NewsRepository

class NewsFeedViewModel :ViewModel() {

    val newsArticles=NewsRepository().getNewsArticles().asLiveData()
}