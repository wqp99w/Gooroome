package com.wqp99w.gooroome.communication

import android.util.Log
import retrofit2.HttpException

class GetUser {
    suspend fun User_Data(userid : String) : List<User>{
        val apiService = RetrofitComu.client.create(ComuApi::class.java)
        val response = apiService.getUserByUserId(userid)

        return if (response.isSuccessful && response.body() != null) {
            response.body()!!
        } else {
            Log.e("MainActivity", "Response not successful or body is null")
            emptyList()
        }
    }


    suspend fun userExists(userid: String): Boolean {
        val apiService = RetrofitComu.client.create(ComuApi::class.java)
        return try {
            apiService.getExistsUserId(userid)
        } catch (e: Exception) {
            Log.e("GetUser", "Error checking user existence", e)
            false
        }
    }

    suspend fun PassLogin(id: String,password: String): Boolean {
        val apiService = RetrofitComu.client.create(ComuApi::class.java)
        return try {
            apiService.getPassLogin(id,password)
        } catch (e: Exception) {
            Log.e("GetUser", "Error checking user existence", e)
            false
        }
    }

    suspend fun createUser(user: User): User? {
        val apiService = RetrofitComu.client.create(ComuApi::class.java)
        return try {
            Log.d("유저유저", user.toString())
            Log.d("회원가입 성공", "굿굿")
            apiService.createUser(user)
        } catch (e: HttpException) {
            Log.e("UserRepository", "HTTP error: ${e.code()} ${e.message()}")
            null
        } catch (e: Exception) {
            Log.e("UserRepository", "Error creating user", e)
            null
        }
    }
}