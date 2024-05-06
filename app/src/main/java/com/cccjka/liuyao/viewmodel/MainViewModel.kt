package com.cccjka.liuyao.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cccjka.liuyao.utils.OkHttpClient
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    fun lunarInfo(date: String){
        viewModelScope.launch {
            OkHttpClient.getRequest(date)
        }
    }
}