package com.fetchreward.hiring.ui.list

import com.fetchreward.hiring.api.RetrofitService
import com.fetchreward.hiring.model.HiringItem
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class HiringListRepository {

    /**
     * The function returns list of HiringItems
     * @return  List<HiringItems> after receiving successful response
     *          emptyList() after receiving an error code
     */
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

    /**
     * The function sort by listId and then by the name.
     * @param hiringList : raw data in list containing null and invalid data, unsorted and unfiltered
     * @return List<HiringItem> sorted with listId, then by the Item name after filtering invalid data
     */
    private fun sort(hiringList: List<HiringItem>): List<HiringItem> {
        return hiringList.sortedWith(compareBy<HiringItem> {item -> item.listId}
            .thenBy { item -> item.name.filter { it.isDigit() } }
        )
    }
}