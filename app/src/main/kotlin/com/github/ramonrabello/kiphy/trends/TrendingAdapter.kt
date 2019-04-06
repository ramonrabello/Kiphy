package com.github.ramonrabello.kiphy.trends

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.ramonrabello.kiphy.R
import com.github.ramonrabello.kiphy.common.extensions.slugfy
import com.github.ramonrabello.kiphy.common.ui.util.ColoredPlaceholderGenerator
import com.github.ramonrabello.kiphy.trends.model.DataResponse

/**
 * Adapter for trending items.
 */
class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private val data = mutableListOf<DataResponse>()

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.trending_view_holder,
                        parent, false)
        return TrendingViewHolder(view)
    }

    override fun getItemCount() = data.size

    fun addTrends(newTrends: List<DataResponse>) {
        data.addAll(newTrends)
        notifyItemRangeInserted(data.size + 1, newTrends.size)
    }

    /**
     * View holder for a trending item.
     */
    class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gifImage by lazy { itemView.findViewById<ImageView>(R.id.gif_image) }
        private val gitSlug by lazy { itemView.findViewById<TextView>(R.id.gif_slug) }

        /**
         * Binds data to view holder views.
         */
        fun bind(data: DataResponse) {
            itemView.setOnClickListener {
                // TODO: handle on click event later on
            }

            Glide.with(itemView.context)
                    .asGif()
                    .load(data.images.fixed_width.url)
                    .apply(RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(ColorDrawable(
                                    ColoredPlaceholderGenerator.generate(itemView.context)))
                            .centerCrop())
                    .transition(DrawableTransitionOptions.withCrossFade(250))
                    .into(gifImage)
            gitSlug.text = data.slug.slugfy()
        }
    }
}