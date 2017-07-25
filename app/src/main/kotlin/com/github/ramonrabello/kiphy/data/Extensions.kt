package com.github.ramonrabello.kiphy.data

import android.view.View

/**
 * Created by ramonrabello on 11/07/17.
 */
fun View.visible() = this.run { visibility = View.VISIBLE }
fun View.gone() = this.run {  visibility = View.GONE }

/**
 * Extension function that converts the slug
 * attribute value of type 'tag1-tag2-tag3-<slug_hash>' into
 * '#tag1 #tag2 #tag3' format.
 */
fun String.tagfy() : String {
    var slug:String? = ""

    // splits words using delimiter '-'
    this.split("-")

            // map each list element to include '#' as first char
            .map{ it -> "#$it " }

            // ignore the last occurrence in list which is the slug hash
            .dropLast(1)

            // concat all elements and save in a final String
            .forEach { it ->  slug += it }

    return slug?.trim() ?: "No slug" // remove trailing spaces
}