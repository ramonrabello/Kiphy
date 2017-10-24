package com.github.ramonrabello.kiphy.data

import com.github.ramonrabello.kiphy.BuildConfig
import com.github.ramonrabello.kiphy.presentation.trends.TrendingEndpoint
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that will handle access to Giphy API endpoints
 * in a fluent way.
 */
object GiphyApi {

    private var retrofit: Retrofit
    private const val BASE_URL = "http://api.giphy.com"

    init {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor {
            chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("api_key", BuildConfig.GIPHY_API_KEY)
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        if (BuildConfig.DEBUG){
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            okHttpClient.addInterceptor(loggingInterceptor)
        }

        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
    }

    fun trending(): TrendingEndpoint = retrofit.create(TrendingEndpoint::class.java)
}