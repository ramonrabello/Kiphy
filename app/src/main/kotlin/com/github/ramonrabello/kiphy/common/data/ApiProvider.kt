package com.github.ramonrabello.kiphy.common.data

import com.github.ramonrabello.kiphy.BuildConfig
import com.github.ramonrabello.kiphy.trends.TrendingApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton that handle all network setup for API calls.
 */
object ApiProvider {
    private const val API_KEY_PARAM = "api_key"
    private const val BASE_URL = "http://api.giphy.com"
    private const val TIMEOUT_LIMIT_IN_MILLIS = 30_000L
    private var retrofit: Retrofit

    init {
        val okHttpClient = buildOkHttpClient()
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun buildOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader(API_KEY_PARAM, BuildConfig.GIPHY_API_KEY)
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            okHttpClient.connectTimeout(TIMEOUT_LIMIT_IN_MILLIS, TimeUnit.MILLISECONDS)
            okHttpClient.readTimeout(TIMEOUT_LIMIT_IN_MILLIS, TimeUnit.MILLISECONDS)
            okHttpClient.writeTimeout(TIMEOUT_LIMIT_IN_MILLIS, TimeUnit.MILLISECONDS)
            okHttpClient.addInterceptor(loggingInterceptor)
        }
        return okHttpClient.build()
    }

    fun providesTrendingApi(): TrendingApi = retrofit.create(TrendingApi::class.java)
}