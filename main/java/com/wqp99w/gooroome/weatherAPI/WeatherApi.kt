package com.wqp99w.gooroome.weatherAPI

import com.wqp99w.gooroome.weatherAPI.ApiKey.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("getVilageFcst?serviceKey=$API_KEY")
    suspend fun getWeather(
        @Query("dataType") dataType : String,
        @Query("numOfRows") numOfRows : Int,
        @Query("pageNo") pageNo : Int,
        @Query("base_date") baseDate : Int,
        @Query("base_time") baseTime : String,
        @Query("nx") nx : String,
        @Query("ny") ny : String
    ) : Response<Weather>
}