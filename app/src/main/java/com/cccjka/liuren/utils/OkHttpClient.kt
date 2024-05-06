package com.cccjka.liuren.utils

import android.util.Log
import com.cccjka.liuren.api.api
import com.cccjka.liuren.bean.ResponseBean
import com.cccjka.liuren.bean.ResponseData
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class OkHttpClient{
    companion object {
        val okHttpClient = OkHttpClient.Builder().build()
        private var instance: OkHttpClient? = null
            get() {
                if (field == null) field = OkHttpClient()
                return field
            }

        @Synchronized
        fun instance(): OkHttpClient {
            return instance!!
        }

        fun getRequest(date: String) {
            val request = Request.Builder()
                .url("https://www.36jxs.com/api/Commonweal/almanac?sun=" + date)
                .method("GET", null)
                .build()

            val url = URL("https://www.36jxs.com/")
            val retrofit = Retrofit.Builder().baseUrl(url).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson())).build()
                .create(api::class.java)
            retrofit.getInfo(date).enqueue(object : retrofit2.Callback<ResponseBean<ResponseData>> {
                override fun onResponse(
                    call: retrofit2.Call<ResponseBean<ResponseData>>,
                    response: retrofit2.Response<ResponseBean<ResponseData>>
                ) {
                    val responseData = response.body()
                    val str = responseData?.data?.Ji
                    str?.let { Log.e("handle", it) }
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

}