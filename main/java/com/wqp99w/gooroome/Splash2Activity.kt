package com.wqp99w.gooroome

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import com.wqp99w.gooroome.communication.GetFood
import com.wqp99w.gooroome.communication.GetUser
import com.wqp99w.gooroome.naverSearchApi.NaverApiMain
import com.wqp99w.gooroome.nowPosition.RegionSearch
import com.wqp99w.gooroome.weatherAPI.WeatherDate
import com.wqp99w.gooroome.weatherAPI.WeatherLocation
import com.wqp99w.gooroome.weatherAPI.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Splash2Activity : AppCompatActivity() {
    private val viewModel by viewModels<WeatherViewModel>()

    private val weatherlocation = WeatherLocation()

    val getfood = GetFood()
    val regionsearch = RegionSearch()
    val getuser = GetUser()
    val weatherdate = WeatherDate()
    val naverapimain = NaverApiMain()
    data class XY(var nx :Int, var ny : Int )

    @SuppressLint("MissingPermission")
    private suspend fun getLocation(xy : XY) {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        val userid = intent.getSerializableExtra("userid")
        val user = getuser.User_Data(userid.toString())

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { success: Location? ->
                success?.let { location ->
                    var lat = location.latitude
                    var lon = location.longitude
                    lat = 35.7912 //안드로이드 에뮬레이터에서는 좌표가 미국으로 찍히기 때문에 임의의 한국 좌표로 설정함
                    lon = 127.1208
                    val temp = weatherlocation.convertToXy(lat, lon)
                    xy.nx = temp.nx //WeatherData에 들어갈 XY좌표 변환
                    xy.ny = temp.ny

                    val address = regionsearch.getAddress(lat, lon, Geocoder(this)) //현재 좌표가 무슨 동네인지 리턴
                    Log.d("위치", address)
                    GlobalScope.launch(Dispatchers.Main) {
                        var SkyState = ""
                        var Temperature = ""
                        GlobalScope.launch(Dispatchers.Main) {
                            viewModel.getWeather( //XY좌표로 바꾼 값을 통해서 현재 위치의 날씨와 온도를 가져온다.
                                "JSON", 14, 1, weatherdate.NowDate,
                                weatherdate.NowTime(), xy.nx.toString(), xy.ny.toString()
                            )
                            viewModel.weatherResponse.observe(this@Splash2Activity) { response -> //getweather의 결과에서 날씨와 온도에 해당하는 값만 가져오기
                                for (i in response.body()?.response!!.body.items.item) {
                                    when (i.category) {
                                        "PTY" -> SkyState = i.fcstValue
                                        "TMP" -> Temperature = i.fcstValue
                                    }
                                }
                                val intent =
                                    Intent(this@Splash2Activity, MainActivity::class.java)
                                intent.putExtra("skystate", SkyState)
                                intent.putExtra("temperature", Temperature)
                                intent.putExtra("user", ArrayList(user))
                                intent.putExtra("address", address)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
            }
            .addOnFailureListener { fail ->

            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        var xy = XY(0, 0)
        GlobalScope.launch(Dispatchers.Main) {
            getLocation(xy)
        }
    }
}