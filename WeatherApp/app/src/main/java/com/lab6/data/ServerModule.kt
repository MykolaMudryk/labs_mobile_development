package com.lab6.data

import com.lab6.data.entity.response.WeatherForecastResponse
import com.lab6.data.entity.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * viewmodel gets data from ServerApi by queries
 * Data about weather pass to WeatherScreen using stateflow
 * in ui components these data used for reflection info about weather
 * and user can change city using CityInputDialog
 */

const val API_KEY: String = "80c789b9890960e979a1f32323a5057f" //  https://home.openweathermap.org/api_keys

interface ServerApi {
    /**
    get forecast by city using parameter q in order to pass city
    and parameter appid in order to pass api key by /data/2.5/weather endpoint
     */

    // used "suspend" keyword in order to make queries asynchronous with kotlin coroutines

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiId: String = API_KEY,
        ): WeatherResponse

    /**
    get seven day forecast by city using parameter q in order to pass city
    and parameter appid in order to pass api key by /data/2.5/forecast endpoint
     */

    @GET("/data/2.5/forecast")
    suspend fun getSevenDayForecastByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String = API_KEY,
        ): WeatherForecastResponse

}
