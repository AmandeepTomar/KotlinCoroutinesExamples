package com.amandeep.kotlincoroutinesexamples.view.newFeeds

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amandeep.kotlincoroutinesexamples.R
import com.amandeep.kotlincoroutinesexamples.view.newFeeds.viewmodel.NewsFeedViewModel
import kotlinx.android.synthetic.main.fragment_news_feeds.*

class NewsFeedsFragment :Fragment(R.layout.fragment_news_feeds) {

    private val viewModel by lazy { ViewModelProvider(this)[NewsFeedViewModel::class.java] }
    private val newsAdapter by lazy { NewsListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsList.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=newsAdapter
        }

        observedModel()

    }

    private fun observedModel() {

        viewModel.newsArticles.observe(viewLifecycleOwner){
            loading_view.visibility=View.GONE
            newsList.visibility=View.VISIBLE
            newsAdapter.onAddNewsItem(it)
            newsList.smoothScrollToPosition(0)
        }
    }


}