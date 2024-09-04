package com.wqp99w.gooroome.naverSearchApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiServiceImage {
    @GET("/v1/search/image")
    fun searchimages(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display : Int
    ): Call<SearchResponse>
}