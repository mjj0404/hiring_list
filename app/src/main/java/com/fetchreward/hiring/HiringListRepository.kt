package com.fetchreward.hiring

import com.fetchreward.hiring.api.RetrofitService
import com.fetchreward.hiring.model.HiringItem

class HiringListRepository {

    suspend fun fetchHiringItems(): List<HiringItem> {
        val response = RetrofitService.getInstance().fetchHiringItems()
        val hiringList: List<HiringItem>
        return if (response.isSuccessful) {
            hiringList = response.body()!!.filter { !it.name.isNullOrBlank() }
            sort(hiringList)
        } else {
            emptyList()
        }
    }

    private fun sort(hiringList: List<HiringItem>): List<HiringItem> {
        return hiringList.sortedWith(compareBy<HiringItem> {item -> item.listId}
            .thenBy { item -> item.name.filter { it.isDigit() } }
        )
    }
}