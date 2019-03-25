package com.github.ramonrabello.kiphy.trends

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.ramonrabello.kiphy.R
import com.github.ramonrabello.kiphy.data.model.Data
import com.github.ramonrabello.kiphy.common.extensions.slugfy
import java.util.*

/**
 * Adapter for trending items.
 */
class TrendingAdapter(private val data: List<Data>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trending_view_holder, parent, false)
        return TrendingViewHolder(view)
    }

    override fun getItemCount() = data.size

    /**
     * View holder for a trending item.
     */
    class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gifImage by lazy { itemView.findViewById<ImageView>(R.id.gif_image) }
        private val gitSlug by lazy { itemView.findViewById<TextView>(R.id.gif_slug) }

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
            Glide.with(itemView.context)
                    .asGif()
                    .load(data.images.fixed_height.url)
                    .apply(RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(ColorDrawable(ContextCompat.getColor(itemView.context, placeholderColors[randomIndex - 1])))
                            .centerCrop())
                    .transition(DrawableTransitionOptions.withCrossFade(android.R.anim.fade_in, 300))
                    .into(gifImage)
            gitSlug.text = data.slug.slugfy()
        }
    }
}