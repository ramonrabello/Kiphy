package com.github.ramonrabello.kiphy.presentation.trends

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.github.ramonrabello.kiphy.R
import com.github.ramonrabello.kiphy.data.Data
import com.github.ramonrabello.kiphy.data.tagfy
import kotlinx.android.synthetic.main.trend_view_holder.view.*

/**
 * Adapter for trending items.
 */
class TrendingAdapter(private val data: List<Data>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.trend_view_holder, parent, false)
        return TrendingViewHolder(view)
    }

    override fun getItemCount() = data.size

    /**
     * View holder for a trending item.
     */
    class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Binds data to view holder views.
         */
        fun bind(data: Data) {
            Glide.with(itemView?.context).
                    load(data.images.original.url)
                    .apply(RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .centerCrop())
                    .into(itemView.gif_image)
            itemView.gif_slug.text = data.slug.tagfy()
        }
    }
}