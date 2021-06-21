package com.lfaiska.weather.domain

import com.lfaiska.weather.data.remote.dto.ConsolidatedWeatherResponse
import com.lfaiska.weather.data.remote.dto.WeatherLocalResponse
import com.lfaiska.weather.data.repository.Repository
import com.lfaiska.weather.domain.model.Weather
import com.lfaiska.weather.domain.model.WeatherLocal
import com.lfaiska.weather.domain.usecase.GetWeatherFromLocationUseCase
import com.lfaiska.weather.domain.usecase.GetWeatherFromLocationUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class GetWeatherFromLocationUseCaseTest {
    private lateinit var useCase: GetWeatherFromLocationUseCase

    @MockK
    private lateinit var repository: Repository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetWeatherFromLocationUseCaseImpl(repository)
    }

    @Test
    fun `Given the use case get weather from repository successfully then returns a WeatherLocation`() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val mockerWeatherLocalResponse = WeatherLocalResponse(
            listOf(
                ConsolidatedWeatherResponse(
                    "hr",
                    dateFormat.parse("2021-06-21"),
                    11.16,
                    14.04,
                    7.971965692176357,
                    90,
                ),
                ConsolidatedWeatherResponse(
                    "lr",
                    dateFormat.parse("2021-06-24"),
                    10.695,
                    21.15,
                    4.067671183642196,
                    70,
                )
            ),
            title = "London"
        )

        val expectedResult = WeatherLocal(
            "London",
            listOf(
                Weather(
                    "hr",
                    dateFormat.parse("2021-06-21"),
                    11.16,
                    14.04,
                    7.971965692176357,
                    90
                ),
                Weather(
                    "lr",
                    dateFormat.parse("2021-06-24"),
                    10.695,
                    21.15,
                    4.067671183642196,
                    70,
                )
            )
        )

        runBlocking {
            coEvery { repository.getWeatherFromLocation(any()) } returns mockerWeatherLocalResponse

            val result = useCase.execute("123")

            coVerify(Ordering.SEQUENCE) {
                repository.getWeatherFromLocation("123")
            }

            assertEquals(expectedResult, result)
        }
    }
}