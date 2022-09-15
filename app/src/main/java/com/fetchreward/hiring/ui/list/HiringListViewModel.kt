package com.fetchreward.hiring.ui.list

import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetchreward.hiring.model.HiringItem
import kotlinx.coroutines.*
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


class HiringListViewModel() : ViewModel() {

    /**
     * https://developer.android.com/kotlin/coroutines/coroutines-best-practices#mutable-types
     * Hiding mutable datatype
     */
    private val _hiringItemList = MutableLiveData<List<HiringItem>>()
    val hiringItemList = _hiringItemList
    private val _hiringItemListFailed = MutableLiveData<Boolean>()
    val hiringItemListFailed = _hiringItemListFailed

    private val repository: HiringListRepository = HiringListRepository()

    /**
     * The val is passed along with Dispatchers to print stacktrace in log soley for debugging purpose
     */
    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    /**
     * This function check if the device is online by performing DNS lookup first, then
     * perform Retrofit GET request to retrieve, and update the list of hiring candidates.
     * The GET request will not be made if the device is
     * offline, or failed to lookup Google's public DNS server in 1500 milliseconds.
     */
    fun fetchItems() {
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val list: List<HiringItem> = if (isOnline()) {
                repository.fetchHiringItems()
            } else {
                emptyList()
            }
            withContext(Dispatchers.Main) {
                if (list.isNotEmpty()) {
                    hiringItemListFailed.value = false
                    hiringItemList.postValue(list)
                }
                else {
                    onError()
                }
            }
        }
    }

    /**
     * The function lookup the Google's public DNS to check if the user's device is online without
     * requiring ACCESS_NETWORK_STATE permission. The default time-out is set to 1500 millisecond
     * @author: Levite (https://stackoverflow.com/a/27312494/6379059)
     * @return true : when the network socket connects in time
     * @return false : when the IOException is thrown
     */
    private fun isOnline(): Boolean {
        return try {
            val timeoutMs = 1500
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)

            socket.connect(socketAddress, timeoutMs)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }

    private fun onError() {
        hiringItemListFailed.value = true
    }
}