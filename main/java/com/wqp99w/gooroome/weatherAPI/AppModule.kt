package com.wqp99w.gooroome.weatherAPI

import com.wqp99w.gooroome.weatherAPI.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    //아래와 같이 의존성을 제공하면 다른 클래스에서 inject를 사용하면 자동으로 실행되는 것

    @Provides //이건 이 메서드가 의존성을 제공할거라고 명시하는 것
    @Singleton
    //Retrofit은 API통신에 사용되는 라이브러리임
    fun provideRetrofit() : Retrofit =
        //retrofit 객체를 생성
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            //JSON데이터를 파싱하기 위해서 사용, 파싱은 해당 데이터를 프로그래밍 언어에서 쉽게 사용할 수 있도록 바꾸는 것
            .addConverterFactory(GsonConverterFactory.create())
            .build() //생성

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit : Retrofit) : WeatherApi =
        retrofit.create(WeatherApi::class.java) //WeatherApi를 구현한 객체를 만드는 코드


}
