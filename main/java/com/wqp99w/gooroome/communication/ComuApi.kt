package com.wqp99w.gooroome.communication

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ComuApi {
    @GET("/api/foods")
    fun getFoods(): Call<List<Food>>

    @GET("/api/categoris")
    suspend fun getFoodsByCategory( //이러면 스프링 서버에서 선호도 두개에 해당하는 속성중 하나를 랜덤으로 가져온다.
        @Query("cate1") cate1: String,
        @Query("cate2") cate2: String
    ): Response<List<Food>>

    @GET("/api/users/preferences")
    suspend fun getUserByUserId(
        @Query("userid") userid: String
    ): Response<List<User>>

    @GET("/api/users/existsId")
    suspend fun getExistsUserId(
        @Query("userid") userid: String
    ): Boolean//Call<List<Food>>

    @GET("/api/users/Login")
    suspend fun getPassLogin(
        @Query("userid") userid: String,
        @Query("password") password: String
    ): Boolean//Call<List<Food>>

    @POST("/api/users/create")
    suspend fun createUser(@Body user: User): User

}