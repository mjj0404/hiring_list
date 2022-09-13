package com.fetchreward.hiring.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fetchreward.hiring.model.HiringItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HiringListViewModel() : ViewModel() {

    val hiringItemList = MutableLiveData<List<HiringItem>>()
    val hiringItemListFailed = MutableLiveData<Boolean>()
    private var ready: Boolean = false

    private val repository: HiringListRepository = HiringListRepository()

    fun fetchItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = repository.fetchHiringItems()
            withContext(Dispatchers.Main) {
                if (list.isNotEmpty()) {
                    hiringItemListFailed.value = false
                    hiringItemList.postValue(list)
                    ready = true
                }
                else {
                    onError()
                }
            }
        }
    }

    fun isReady(): Boolean {
        return ready
    }

    private fun onError() {
        hiringItemListFailed.value = true
    }
}