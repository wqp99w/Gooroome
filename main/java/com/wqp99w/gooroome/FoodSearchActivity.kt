package com.wqp99w.gooroome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.wqp99w.gooroome.communication.User
import com.wqp99w.gooroome.databinding.ActivityFoodSearchActivityBinding
import com.wqp99w.gooroome.naverSearchApi.SearchItem

class FoodSearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFoodSearchActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food_search_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_search_activity)
        binding.lifecycleOwner = this
        val foodItems = intent.getSerializableExtra("asd") as ArrayList<SearchItem>
        val foodImages = intent.getSerializableExtra("wqe") as ArrayList<SearchItem>
        val userdata = intent.getSerializableExtra("user") as List<User>
        val skystate = intent.getSerializableExtra("skystate")
        val temperature = intent.getSerializableExtra("temperature")
        val address = intent.getSerializableExtra("address")

        Log.d("서브 하늘", skystate.toString())
        Log.d("서브 온도", temperature.toString())
        Log.d("서브 유저", userdata[0].toString())


        val t1=binding.text1Id
        val t2=binding.text2Id
        val t4=binding.text4Id
        val t5=binding.text5Id
        val t7=binding.text7Id
        val t8=binding.text8Id
        val btn1=binding.btnId1
        val img1 = binding.img1Id
        val img2 = binding.img2Id
        val img3 = binding.img3Id

        t1.text = foodItems[0].title
        t2.text = foodItems[0].link
        t4.text = foodItems[1].title
        t5.text = foodItems[1].link
        t7.text = foodItems[2].title
        t8.text = foodItems[2].link

        Glide.with(this)
            .load(foodImages[0].link)
            .into(img1)
        Glide.with(this)
            .load(foodImages[1].link)
            .into(img2)
        Glide.with(this)
            .load(foodImages[2].link)
            .into(img3)


        Log.d("안녕","안녕안녕")

        btn1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) //Intent에서 처음 값은 현재 위치, 다음 값은 넘어갈 위치임
            intent.putExtra("skystate", skystate)
            intent.putExtra("temperature", temperature)
            intent.putExtra("user", ArrayList(userdata))
            intent.putExtra("address", address)
            startActivity(intent)
        }

    }
}