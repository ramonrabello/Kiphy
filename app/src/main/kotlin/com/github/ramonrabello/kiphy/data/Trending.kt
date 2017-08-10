package com.github.ramonrabello.kiphy.data

/**
 * Represents a trending from Giphy Trending API.
 */
data class Trending(val data:List<Data>)
data class Data(val slug:String, val images: Images)
data class Images(val fixed_width: FixedWidthImage, val fixed_height: FixedHeightImage, val original: OriginalImage)
data class FixedWidthImage(val url:String, val width:Int, val height:Int)
data class FixedHeightImage(val url:String, val width:Int, val height:Int)
data class OriginalImage(val url:String, val width:Int, val height:Int)