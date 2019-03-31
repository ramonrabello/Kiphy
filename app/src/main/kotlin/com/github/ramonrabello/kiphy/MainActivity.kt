package com.github.ramonrabello.kiphy

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.ramonrabello.kiphy.common.extensions.gone
import com.github.ramonrabello.kiphy.common.extensions.visible
import com.github.ramonrabello.kiphy.trends.SpacingItemDecoration
import com.github.ramonrabello.kiphy.trends.TrendingAdapter
import com.github.ramonrabello.kiphy.trends.TrendingContract
import com.github.ramonrabello.kiphy.trends.TrendingPresenter
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import com.google.android.material.snackbar.Snackbar

/**
 * Main Activity for the app.
 */
class MainActivity : AppCompatActivity(), TrendingContract.View {

    private val trendingAdapter by lazy { TrendingAdapter() }
    private val trendingPresenter by lazy { TrendingPresenter(this) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progress_bar) }
    private val trendsRecyclerView by lazy { findViewById<RecyclerView>(R.id.trends_recycler_view) }
    private val coordinatorLayout by lazy { findViewById<CoordinatorLayout>(R.id.coordinator_layout) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        trendsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_item_decoration_offset)))
            adapter = trendingAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        trendingPresenter.loadTrends()
    }

    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.gone()
        trendsRecyclerView.visible()
    }

    override fun showTrending(trending: TrendingResponse) {
        trendingAdapter.addTrends(trending.data)
    }

    override fun showTrendingError() {
        Snackbar.make(coordinatorLayout, getString(R.string.trending_error), Snackbar.LENGTH_SHORT).show()
    }

    override fun showApiKeyNotSetDialog() {
        val alertDialogError = AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title))
                .setPositiveButton(getString(R.string.dialog_close_button), null)
                .setMessage(getString(R.string.api_key_error))
                .create()
        alertDialogError.show()
    }
}
