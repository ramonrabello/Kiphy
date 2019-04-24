package com.github.ramonrabello.kiphy.trends.data

import com.github.ramonrabello.kiphy.trends.TrendingApi
import com.github.ramonrabello.kiphy.trends.data.source.TrendingDataSource
import com.github.ramonrabello.kiphy.trends.data.source.remote.RemoteTrendingDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RemoteTrendingDataSourceTest {

    private lateinit var api: TrendingApi

    private lateinit var remoteTrendingDataSource: TrendingDataSource

    @Before
    fun setUp() {
        api = mock(TrendingApi::class.java)
        remoteTrendingDataSource = RemoteTrendingDataSource(api)
    }

    @Test
    fun `when loading trending verify if api was called`() {
        remoteTrendingDataSource.loadTrending()
        verify(api).loadTrending()
    }
}