package com.fetchreward.hiring.api

import android.content.res.Resources
import com.fetchreward.hiring.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private var instance: ApiClient? = null
    private var retrofit: Retrofit? = null
    private var apiService: HiringListApi? = null

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Resources.getSystem().getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}