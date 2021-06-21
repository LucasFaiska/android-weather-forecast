package com.lfaiska.weather.data.repository

import com.lfaiska.weather.data.remote.Service
import com.lfaiska.weather.data.remote.dto.WeatherLocationResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class RepositoryTest {

    lateinit var repository: Repository

    @MockK
    lateinit var service: Service

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = RepositoryImpl(service)
    }

    @Test
    fun `Given the repository get weather from service successfully then returns a WeatherLocationResponse`() {
        runBlocking {
            val mockedWeatherLocationResponse = mockk<WeatherLocationResponse>()

            coEvery {
                service.getWeatherFromLocation(any())
            } returns mockedWeatherLocationResponse

            val result = repository.getWeatherFromLocation("123")

            coVerify (ordering = Ordering.SEQUENCE) {
                service.getWeatherFromLocation("123")
            }

            assertEquals(mockedWeatherLocationResponse, result)
        }
    }

    @Test
    fun `Given the repository get weather from service fails then throws a RepositoryException`() {
        runBlocking {
            val mockedException = mockk<Exception>()

            coEvery {
                service.getWeatherFromLocation(any())
            } throws mockedException

            try {
                repository.getWeatherFromLocation("123")
            } catch (exception: RepositoryException) {
                assertEquals(mockedException, exception.cause)
            }

            coVerify (ordering = Ordering.SEQUENCE) {
                service.getWeatherFromLocation("123")
            }
        }
    }

}