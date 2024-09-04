package com.wqp99w.gooroome.nowPosition

import android.location.Address
import android.location.Geocoder
import android.util.Log
import java.io.IOException

class RegionSearch {
    fun getAddress (lat : Double, lon : Double, geocoder: Geocoder) : String {
        var address: List<Address>? = null
        try {
            address = geocoder.getFromLocation(lat, lon, 10)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("test", "입출력오류")
        }
        if (address != null) {
            if (address.size == 0) {
                Log.d("찾은 주소", address[0].toString())
            } else {
                Log.d("찾은 주소", address[0].toString())
                return address[0]?.thoroughfare.toString()
            }
        }
        return "error"
    }
}