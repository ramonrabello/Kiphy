package com.github.ramonrabello.kiphy.trends.model

/**
 * Represents a trending from Giphy Trending API.
 */
data class TrendingResponse(val data: List<DataResponse>)
data class DataResponse(val slug: String, val images: ImagesResponse)
data class ImagesResponse(val fixed_width: ImageSizeResponse,
                          val fixed_height: ImageSizeResponse,
                          val original: ImageSizeResponse)
data class ImageSizeResponse(val url: String)