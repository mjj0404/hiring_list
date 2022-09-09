package com.fetchreward.hiring.api

import com.fetchreward.hiring.model.HiringItem
import retrofit2.http.GET

interface HiringListApi {
    @GET("hiring.json")
    suspend fun fetchItems(): List<HiringItem>
}