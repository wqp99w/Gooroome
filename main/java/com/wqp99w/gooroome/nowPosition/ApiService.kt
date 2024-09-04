package com.wqp99w.gooroome.nowPosition

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/maps-reversegeocoding-gc/v1/coords-to-addresses")
    fun getAddress(
        @Query("coords") coords: String,
        @Query("output") output: String = "json"
    ): Call<AddressResponse>
}

data class AddressResponse(
    val status: Status,
    val results: List<Result>
)

data class Status(
    val code: Int,
    val name: String
)

data class Result(
    val name: String,
    val region: Region
)

data class Region(
    val area1: Area,
    val area2: Area,
    val area3: Area
)

data class Area(
    val name: String
)