package com.github.ramonrabello.kiphy.presentation.trends

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.github.ramonrabello.kiphy.R
import com.github.ramonrabello.kiphy.data.Data
import com.github.ramonrabello.kiphy.data.tagfy
import kotlinx.android.synthetic.main.trending_view_holder.view.*
import java.util.*

/**
 * Adapter for trending items.
 */
class TrendingAdapter(private val data: List<Data>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.trending_view_holder, parent, false)
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
            //itemView.tag = data
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/gif"
                //intent.putExtra(Intent.EXTRA_STREAM, itemView.gif_image.drawable as GifDrawable)
            }

            val placeholderColors = arrayOf(
                    R.color.colorLightGreen,
                    R.color.colorLightBlue,
                    R.color.colorLightPurple,
                    R.color.colorLightRed,
                    R.color.colorLightYellow
            )

            // choose random color to be the image placeholder
            val randomIndex = Random().nextInt(placeholderColors.size + 1 - 1) + 1
            Glide.with(itemView?.context)
                    .asGif()
                    .load(data.images.fixed_height.url)
                    .apply(RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(ColorDrawable(ContextCompat.getColor(itemView.context, placeholderColors[randomIndex-1])))
                            .centerCrop())
                    .transition(DrawableTransitionOptions.withCrossFade(android.R.anim.fade_in, 300))
                    .into(itemView.gif_image)
            itemView.gif_slug.text = data.slug.tagfy()
        }
    }
}