package com.github.ramonrabello.kiphy

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import com.github.ramonrabello.kiphy.data.Trending
import com.github.ramonrabello.kiphy.data.gone
import com.github.ramonrabello.kiphy.data.visible
import com.github.ramonrabello.kiphy.presentation.trends.SpacingItemDecoration
import com.github.ramonrabello.kiphy.presentation.trends.TrendingAdapter
import com.github.ramonrabello.kiphy.presentation.trends.TrendingContract
import com.github.ramonrabello.kiphy.presentation.trends.TrendingPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Activity for the app.
 */
class MainActivity : AppCompatActivity(), TrendingContract.View {

    private lateinit var trendingPresenter:TrendingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        trendingPresenter = TrendingPresenter(this)
        trendingPresenter.loadTrending()
    }

    override fun showProgress() {
        progress_bar.visible()
    }

    override fun hideProgress() {
        progress_bar.gone()
        trends_recycler_view.visible()
    }

    override fun showTrending(trending: Trending) {

        trends_recycler_view.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(SpacingItemDecoration(10))
            adapter = TrendingAdapter(trending.data)
        }
    }

    override fun showTrendingError() {
        Snackbar.make(coordinator_layout, getString(R.string.trending_error), Snackbar.LENGTH_SHORT).show()
    }

    override fun showApikeyError() {
        val alertDialogError = AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title))
                .setPositiveButton(getString(R.string.dialog_close_button), null)
                .setMessage(getString(R.string.api_key_error))
                .create()
        alertDialogError.show()
    }
}
