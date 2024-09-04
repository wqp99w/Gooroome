package com.wqp99w.gooroome.weatherAPI

import android.icu.util.Calendar

class WeatherDate {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val NowDate = 10000*year+month*100+day
    fun NowTime(): String{
        return when {
            hour < 5 -> "0200"
            hour < 8 -> "0500"
            hour < 11 -> "0800"
            hour < 14 -> "1100"
            hour < 17 -> "1400"
            hour < 20 -> "1700"
            hour < 23 -> "2000"
            else -> "2300"
        }
    }

}