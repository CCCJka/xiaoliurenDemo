package com.cccjka.liuren.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cccjka.liuren.bean.ResponseData
import com.cccjka.liuren.utils.OkHttpClient
import kotlinx.coroutines.launch

class LunarInfoViewModel: ViewModel() {

    fun lunarInfo(date: String){
        viewModelScope.launch {
            OkHttpClient.getRequest(date)
        }
//        return
    }
}