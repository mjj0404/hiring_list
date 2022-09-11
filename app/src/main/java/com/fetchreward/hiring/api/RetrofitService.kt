package com.fetchreward.hiring.api

import com.fetchreward.hiring.model.HiringItem
import com.fetchreward.hiring.utility.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("/hiring.json")
    suspend fun fetchHiringItems(): Response<List<HiringItem>>

    companion object {
        private var retrofitInstance: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitInstance == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitInstance = retrofit.create(RetrofitService::class.java)
            }
            return retrofitInstance!!
        }
    }
}