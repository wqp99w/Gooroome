package com.wqp99w.gooroome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wqp99w.gooroome.communication.GetFood
import com.wqp99w.gooroome.communication.User
import com.wqp99w.gooroome.databinding.ActivityMainBinding
import com.wqp99w.gooroome.naverSearchApi.NaverApiMain
import com.wqp99w.gooroome.naverSearchApi.SearchItem
import com.wqp99w.gooroome.weatherAPI.RequestPermissionsUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    val getfood = GetFood()
    val naverapimain = NaverApiMain()
    data class XY(var nx :Int, var ny : Int )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        val btn = binding.BtnId
        val text = binding.TextId
        val userdata = intent.getSerializableExtra("user") as List<User>
        val skystate = intent.getSerializableExtra("skystate")
        val temperature = intent.getSerializableExtra("temperature")
        val address = intent.getSerializableExtra("address")

        Log.d("메인 하늘", skystate.toString())
        Log.d("메인 온도", temperature.toString())
        Log.d("메인 유저", userdata[0].toString())
        var xy = XY(0,0)
        btn.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val Foodcate1 = getfood.WeatherFood(temperature.toString(), skystate.toString())
                var Foodcate2 = getfood.PreFood(
                    userdata[0].one,
                    userdata[0].two,
                    userdata[0].three,
                    userdata[0].four
                )
                Log.d("테세테ㅔㅔㅔ테ㅛ세ㅛㅔㅛ테ㅛㅌ",Foodcate1)
                Log.d("테세테ㅔㅔㅔ테ㅛ세ㅛㅔㅛ테ㅛㅌ",Foodcate2)
                val fooddata = getfood.Food_Data(Foodcate2, Foodcate1)
                Log.d("테세테ㅔㅔㅔ테ㅛ세ㅛㅔㅛ테ㅛㅌ",fooddata.toString())
                GlobalScope.launch(Dispatchers.Main) {
                    Log.d("푸드푸드", fooddata[0].food)
                    val getrest = naverapimain.GetRest(address.toString(), fooddata[0].food)
                    Log.d("실험", getrest[0].items[0].title)
                    val intent =
                        Intent(this@MainActivity, FoodSearchActivity::class.java)
                    Log.d("넘겨줘요", getrest[0].items[0].title)
                    Log.d("넘겨줘요", getrest[1].items.toString())
                    intent.putExtra("asd", getrest[0].items as ArrayList<SearchItem>)
                    intent.putExtra("wqe", getrest[1].items as ArrayList<SearchItem>)
                    intent.putExtra("skystate", skystate)
                    intent.putExtra("temperature", temperature)
                    intent.putExtra("user", ArrayList(userdata))
                    intent.putExtra("address", address)
                    startActivity(intent)
                }
            }
        }


    }



    override fun onStart() {
        super.onStart()
        RequestPermissionsUtil(this).requestLocation() // 위치 권한 요청
    }


}