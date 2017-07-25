package com.github.ramonrabello.kiphy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.progress_bar
import kotlinx.android.synthetic.main.activity_main.trends_recycler_view
import com.github.ramonrabello.kiphy.data.Trending
import com.github.ramonrabello.kiphy.data.gone
import com.github.ramonrabello.kiphy.data.visible
import com.github.ramonrabello.kiphy.presentation.trends.TrendingAdapter
import com.github.ramonrabello.kiphy.presentation.trends.TrendingContract
import com.github.ramonrabello.kiphy.presentation.trends.TrendingPresenter

/**
 * Main Activity for the app.
 */
class MainActivity : AppCompatActivity(), TrendingContract.View {

    lateinit var trendingPresenter:TrendingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        trendingPresenter = TrendingPresenter(this)
        trendingPresenter.loadTrends()
    }

    override fun showLoading() {
        progress_bar.visible()
        trends_recycler_view.gone()
    }

    override fun hideLoading() {
        progress_bar.gone()
        trends_recycler_view.visible()
    }

    override fun showTrends(trending: Trending?) {
        trends_recycler_view.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = TrendingAdapter(trending!!.data)
        }

    }

    override fun onLoadingTrendsError() {
        Toast.makeText(this, "Oh oh! Error loading trends :(", Toast.LENGTH_SHORT).show()
    }
}
