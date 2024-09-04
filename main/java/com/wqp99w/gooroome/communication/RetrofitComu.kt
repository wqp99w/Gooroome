package com.wqp99w.gooroome.communication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitComu {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://port-0-food-spring-ss7z32llwczoa2p.sel5.cloudtype.app/"

    val client: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
}