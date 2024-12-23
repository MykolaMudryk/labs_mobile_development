package com.lab6.data.entity.response

import com.lab6.data.entity.WeatherMain

// data class about current forecast

data class WeatherResponse(
    val main: WeatherMain?,
    val weather: List<WeatherDescription>,

)
