package com.example.mangolia.vewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mangolia.base.BaseViewModel
import com.example.mangolia.models.Root
import com.example.mangolia.repository.MainRepo
import kotlinx.coroutines.launch

class DashBoardViewModel constructor(private val repository: MainRepo) : BaseViewModel() {

    private val TAG = "DashBoardViewModel"
    val dataFeed: MutableLiveData<Root> by lazy {
        MutableLiveData<Root>()
    }

    fun getFeed() = viewModelScope.launch {
        eventShowLoading.value = true
        try{
            val feed = repository.getNewsFeed()
                this@DashBoardViewModel.dataFeed.value = feed.body()
            Log.d(TAG, "getFeed: ${dataFeed.value?.data} ")
        }catch (e: Exception){
            eventShowMessage.value = e.message
        }
        eventShowLoading.value = false
    }

}