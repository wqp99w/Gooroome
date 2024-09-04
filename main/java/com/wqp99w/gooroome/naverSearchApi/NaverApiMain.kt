package com.wqp99w.gooroome.naverSearchApi

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NaverApiMain {
    suspend fun Service(query : String) :SearchResponse {
        val clientId = "LehvnolDvQ3Dh4hnnT0E"
        val clientSecret = "6eLKj1UnTr"

        val apiService = ApiClient.create()
        return withContext(Dispatchers.IO) {
            val call = apiService.searchLocals(clientId, clientSecret, query, 3).execute()
            if (call.isSuccessful) {
                val body = call.body()
                body?.items?.take(3)?.let { items ->
                }
                body ?: throw IllegalStateException("Response body is null")
            } else {
                Log.d("에러", "뷀ㄺ뤱루게ㅜㄹ베ㅜㄹ게ㅜㄹ")
                throw IllegalStateException("Response unsuccessful: ${call.code()}")
            }
        }
    }

    suspend fun img_Service(query : String) : SearchResponse {
        val clientId = "LehvnolDvQ3Dh4hnnT0E"
        val clientSecret = "6eLKj1UnTr"

        val apiService = ApiClient.img_create()
        return withContext(Dispatchers.IO) {
            val call = apiService.searchimages(clientId, clientSecret, query, 3).execute()
            if (call.isSuccessful) {
                val body = call.body()
                body ?: throw IllegalStateException("Response body is null")
            } else {
                Log.d("에러", "뷀ㄺ뤱루게ㅜㄹ베ㅜㄹ게ㅜㄹ")
                throw IllegalStateException("Response unsuccessful: ${call.code()}")
            }
        }
    }

    suspend fun GetRest(address : String, food : String) : List<SearchResponse> {
        val FoodRestarunt = Service("$address $food")
        val FoodImage_list = mutableListOf<SearchItem>()
        val OutList = mutableListOf<SearchResponse>()
        Log.d("네이버1", "네이버 가게 검색 시작")
        FoodRestarunt.items.forEach { item ->
            val image =
                img_Service("${item.title}").items.firstOrNull() // 해당 링크의 첫 번째 이미지
            image?.let {
                FoodImage_list.add(it) // 이미지가 존재하면 리스트에 추가
            }
        }
        Log.d("네이버2", "네이버 가게 검색 종료")
        OutList.add(FoodRestarunt)
        OutList.add(SearchResponse(FoodImage_list))
        Log.d("네이버3", "결과 출력")
        return OutList
    }
}