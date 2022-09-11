package com.fetchreward.hiring

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fetchreward.hiring.api.RetrofitService

import com.fetchreward.hiring.model.HiringItem
import kotlinx.coroutines.*


class HiringListViewModel() : ViewModel() {

    val hiringItemList = MutableLiveData<List<HiringItem>>()
    val hiringItemListFailed = MutableLiveData<Boolean>()

    private val repository: HiringListRepository = HiringListRepository()

    fun fetchItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = repository.fetchHiringItems()
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

    private fun onError() {
        hiringItemListFailed.value = true
    }
}