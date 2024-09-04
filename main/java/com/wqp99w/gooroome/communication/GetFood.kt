package com.wqp99w.gooroome.communication

import android.util.Log
import kotlin.random.Random

class GetFood {
    suspend fun Food_Data(cate1 : String, cate2 : String) : List<Food>{
        val apiService = RetrofitComu.client.create(ComuApi::class.java)
        val response = apiService.getFoodsByCategory(cate1, cate2)

        return if (response.isSuccessful && response.body() != null) {
            response.body()!!
        } else {
            Log.e("MainActivity", "Response not successful or body is null")
            emptyList()
        }
    }

    fun WeatherFood (tem:String, wea:String):String{
        val Ntem = when {
            tem.toDouble() < 5 -> "추움"
            tem.toDouble() < 25 -> "따뜻"
            else -> "더움"
        }

        val Nwea = when (wea.toInt()){
            0 -> "맑음"
            1,2,4 -> "비"
            else -> "눈"
        }

        val WFood = when {
            Ntem=="추움"&&Nwea=="눈" -> arrayOf("Soup","Soup","Soup","Soup","Soup","Rice","Rice","Rice","Noodle","Noodle")
            Ntem=="추움"&&Nwea=="비" -> arrayOf("Soup","Soup","Soup","Soup","Soup","Noodle","Noodle","Noodle","Rice","Rice")
            Ntem=="추움"&&Nwea=="맑음" -> arrayOf("Rice","Rice","Rice","Rice","Rice","Noodle","Noodle","Noodle","Soup","Soup")
            Ntem=="따뜻"&&Nwea=="비" -> arrayOf("Rice","Rice","Rice","Rice","Rice","Soup","Soup","Soup","Noodle","Noodle")
            Ntem=="따뜻"&&Nwea=="눈" -> arrayOf("Rice","Rice","Rice","Rice","Rice","Noodle","Noodle","Noodle","Soup","Soup")
            Ntem=="따뜻"&&Nwea=="맑음" -> arrayOf("Noodle","Noodle","Noodle","Noodle","Noodle","Rice","Rice","Rice","Soup","Soup")
            Ntem=="더움"&&Nwea=="비" -> arrayOf("Noodle","Noodle","Noodle","Noodle","Noodle","Soup","Soup","Soup","Rice","Rice")
            Ntem=="더움"&&Nwea=="눈" -> arrayOf("Noodle","Noodle","Noodle","Noodle","Noodle","Rice","Rice","Rice","Soup","Soup")
            else -> arrayOf("Soup","Soup","Soup","Soup","Soup","Noodle","Noodle","Noodle","Rice","Rice")
        }
        val randomNum = Random.nextInt(10)
        return WFood[randomNum]
    }

    fun PreFood (one:String, two:String, three:String,four:String):String{
        val randomNum = Random.nextInt(10)
        return when{
            randomNum < 4 -> one
            randomNum < 7 -> two
            randomNum < 9 -> three
            else -> four
        }
    }
}