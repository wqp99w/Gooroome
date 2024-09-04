package com.wqp99w.gooroome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wqp99w.gooroome.databinding.ActivityLoginBinding
import com.wqp99w.gooroome.weatherAPI.WeatherLocation
import com.wqp99w.gooroome.weatherAPI.WeatherViewModel
import com.wqp99w.gooroome.communication.GetUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding


    private val viewModel by viewModels<WeatherViewModel>()
    private val weatherlocation = WeatherLocation()
    var SkyState = ""
    var Temperature = ""

    data class XY(var nx :Int, var ny : Int )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            // EditText에서 값을 가져옴
            val id = binding.id.text.toString()
            val password = binding.password.text.toString()

            GlobalScope.launch(Dispatchers.Main){
                val GU = GetUser()
                val login = GU.PassLogin(id, password)
                if(login){
                    Toast.makeText(this@LoginActivity,"로그인 성공", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@LoginActivity, Splash2Activity::class.java)
                    intent.putExtra("userid", id)
                    startActivity(intent)
                    finish()
                }
                else
                    Toast.makeText(this@LoginActivity,"로그인 실패", Toast.LENGTH_LONG).show()

            }

        }
        binding.join.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
