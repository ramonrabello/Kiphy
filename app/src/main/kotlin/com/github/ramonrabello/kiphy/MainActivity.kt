package com.github.ramonrabello.kiphy

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.ramonrabello.kiphy.data.Trending
import com.github.ramonrabello.kiphy.data.gone
import com.github.ramonrabello.kiphy.data.visible
import com.github.ramonrabello.kiphy.presentation.trends.SpacingItemDecoration
import com.github.ramonrabello.kiphy.presentation.trends.TrendingAdapter
import com.github.ramonrabello.kiphy.presentation.trends.TrendingContract
import com.github.ramonrabello.kiphy.presentation.trends.TrendingPresenter
import com.google.android.material.snackbar.Snackbar

/**
 * Main Activity for the app.
 */
class MainActivity : AppCompatActivity(), TrendingContract.View {

    private lateinit var trendingPresenter: TrendingContract.Presenter
    private lateinit var progressBar: ProgressBar
    private lateinit var trendsRecyclerView: RecyclerView
    private lateinit var coordinatorLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progress_bar)
        trendsRecyclerView = findViewById(R.id.trends_recycler_view)
        coordinatorLayout = findViewById(R.id.coordinator_layout)
    }

    override fun onResume() {
        super.onResume()
        trendingPresenter = TrendingPresenter(this)
        trendingPresenter.loadTrending()
    }

    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.gone()
        trendsRecyclerView.visible()
    }

    override fun showTrending(trending: Trending) {

        trendsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(SpacingItemDecoration(10))
            adapter = TrendingAdapter(trending.data)
        }
    }

    override fun showTrendingError() {
        Snackbar.make(coordinatorLayout, getString(R.string.trending_error), Snackbar.LENGTH_SHORT).show()
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
