package com.github.ramonrabello.kiphy.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.ramonrabello.kiphy.R
import com.github.ramonrabello.kiphy.common.arch.state.Result
import com.github.ramonrabello.kiphy.common.arch.state.UiState
import com.github.ramonrabello.kiphy.common.extensions.gone
import com.github.ramonrabello.kiphy.common.extensions.visible
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import com.google.android.material.snackbar.Snackbar

class TrendingFragment : Fragment() {

    private val trendingAdapter by lazy { TrendingAdapter() }
    private lateinit var progressBar: ProgressBar
    private lateinit var trendsRecyclerView: RecyclerView
    private lateinit var parentContainer: View

    companion object {
        fun newInstance() = TrendingFragment()
    }

    private lateinit var viewModel: TrendingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.trending_fragment, container, false)
        parentContainer = view.findViewById(R.id.parent_container)
        progressBar = view.findViewById(R.id.progress_bar)
        trendsRecyclerView = view.findViewById(R.id.trends_recycler_view)
        setupRecyclerView()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        observeUiState()
        observeTrendingData()
        viewModel.loadTrends()
    }

    private fun observeTrendingData() {
        viewModel.trendsDataEvent.observe(this, Observer { state ->
            when (state) {
                is Result.Success -> {
                    showTrending(state.value)
                }
                is Result.Error.ApiKeyNotSetError -> {
                    showApiKeyNotSetDialog()
                }

                is Result.Error.FeatureError.TrendingError -> {
                    showTrendingError()
                }
            }
        })
    }

    private fun observeUiState() {
        viewModel.uiStateEvent.observe(this, Observer { state ->
            when (state) {
                is UiState.Loading -> {
                    showProgress()
                }
                is UiState.Loaded -> {
                    hideProgress()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        trendsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_item_decoration_offset)))
            adapter = trendingAdapter
        }
    }

    private fun showProgress() {
        progressBar.visible()
        trendsRecyclerView.gone()
    }

    private fun hideProgress() {
        progressBar.gone()
        trendsRecyclerView.visible()
    }

    private fun showTrending(trending: TrendingResponse) {
        hideProgress()
        trendingAdapter.addTrends(trending.data)
    }

    private fun showTrendingError() {
        hideProgress()
        Snackbar.make(parentContainer, getString(R.string.trending_error), Snackbar.LENGTH_SHORT).show()
    }

    private fun showApiKeyNotSetDialog() {
        activity?.let { context ->
            val alertDialogError = AlertDialog.Builder(context)
                    .setTitle(getString(R.string.dialog_title))
                    .setPositiveButton(getString(R.string.dialog_close_button), null)
                    .setMessage(getString(R.string.api_key_error))
                    .create()
            alertDialogError.show()
        }
    }
}
