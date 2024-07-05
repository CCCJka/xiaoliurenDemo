package com.cccjka.liuren.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cccjka.liuren.api.api
import com.cccjka.liuren.bean.ResponseBean
import com.cccjka.liuren.bean.ResponseData
import com.cccjka.liuren.navigation.LunarInfoNavigator
import com.cccjka.liuren.utils.CommonUtils
import com.cccjka.liuren.utils.OkHttpClient
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class LunarInfoViewModel: ViewModel() {

    private var responseData: ResponseData = ResponseData()

    private var navigator: LunarInfoNavigator? = null

    var mutableStateInModel = mutableStateOf(responseData)

    fun setNavigator(navigator: LunarInfoNavigator){
        this.navigator = navigator;
    }

    fun request(date: String){
        val url = URL("https://www.36jxs.com/")
        val retrofit = Retrofit.Builder().baseUrl(url).client(OkHttpClient.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson())).build()
            .create(api::class.java)

        retrofit.getInfo(date).enqueue(object : retrofit2.Callback<ResponseBean<ResponseData>> {
            override fun onResponse(
                call: retrofit2.Call<ResponseBean<ResponseData>>,
                response: retrofit2.Response<ResponseBean<ResponseData>>
            ) {
//                responseData = CommonUtils.fromJson(CommonUtils.toJson(response.body()?.data))
                mutableStateInModel.value = response.body()?.data!!
            }

            override fun onFailure(
                call: retrofit2.Call<ResponseBean<ResponseData>>,
                t: Throwable
            ) {
                Log.e("Retrofit", "请求数据失败")
            }
        })
    }
}
