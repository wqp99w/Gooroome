package com.wqp99w.gooroome.weatherAPI

import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

const val COEFFICIENT_TO_RADIAN = Math.PI / 180.0
const val GRID_UNIT_COUNT = 6371.00877 / 5.0  // 지구 반지름 ÷ 정방형 격자 단위 길이 = 격자 개수
const val REF_X = 43.0 // 기준점 X좌표
const val REF_Y = 136.0 // 기준점 Y좌표
const val REF_LON_RAD = 126.0 * COEFFICIENT_TO_RADIAN // 기준점 경도 (rad)
const val REF_LAT_RAD = 38.0 * COEFFICIENT_TO_RADIAN // 기준점 위도 (rad)
const val PROJ_LAT_1_RAD = 30.0 * COEFFICIENT_TO_RADIAN // 투영 위도1 (rad)
const val PROJ_LAT_2_RAD = 60.0 * COEFFICIENT_TO_RADIAN // 투영 위도2 (rad)


data class CoordinatesXy(val nx: Int, val ny: Int)
data class CoordinatesLatLon(val lat: Double, val lon: Double)



class WeatherLocation {
    private val sn = ln(cos(PROJ_LAT_1_RAD) / cos(PROJ_LAT_2_RAD)) / ln(tan(Math.PI * 0.25 + PROJ_LAT_2_RAD * 0.5) / tan(Math.PI * 0.25 + PROJ_LAT_1_RAD * 0.5))
    private val sf = tan(Math.PI * 0.25 + PROJ_LAT_1_RAD * 0.5).pow(sn) * cos(PROJ_LAT_1_RAD) / sn
    private val ro = GRID_UNIT_COUNT * sf / tan(Math.PI * 0.25 + REF_LAT_RAD * 0.5).pow(sn)
    /**
     * Returns the corresponding Cartesian coordinates of the point of [lat] and [lon].
     */
    internal fun convertToXy(lat: Double, lon: Double): CoordinatesXy {
        val ra = GRID_UNIT_COUNT * sf / tan(Math.PI * 0.25 + lat * COEFFICIENT_TO_RADIAN * 0.5).pow(sn)
        val theta: Double = lon * COEFFICIENT_TO_RADIAN - REF_LON_RAD
        val niceTheta = if (theta < -Math.PI) {
            theta + 2 * Math.PI
        } else if (theta > Math.PI) {
            theta - 2 * Math.PI
        }
        else theta

        return CoordinatesXy(
            nx = floor(ra * sin(niceTheta * sn) + REF_X + 0.5).toInt(),
            ny = floor(ro - ra * cos(niceTheta * sn) + REF_Y + 0.5).toInt()
        )
    }



}

