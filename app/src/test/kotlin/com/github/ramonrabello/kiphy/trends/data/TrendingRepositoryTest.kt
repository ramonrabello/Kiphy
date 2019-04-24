package com.github.ramonrabello.kiphy.trends.data

import com.github.ramonrabello.kiphy.trends.data.source.TrendingDataSource
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TrendingRepositoryTest {

    @Mock
    lateinit var localDataSource: TrendingDataSource

    @Mock
    lateinit var remoteDataSource: TrendingDataSource

    private lateinit var repository: TrendingRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = TrendingRepository(remoteDataSource)
    }

    @Test
    fun `when loading repository verify if both remote and local data sources are called`() {
        runBlocking {
//            `when`(remoteDataSource.loadTrendingAsync())
//                    .thenReturn(Single.just(TrendingResponse(emptyList())))
            repository.loadTrendingAsync().await()
            //verify(localDataSource).loadTrendingAsync().await()
            verify(remoteDataSource).loadTrendingAsync().await()
        }
    }
}