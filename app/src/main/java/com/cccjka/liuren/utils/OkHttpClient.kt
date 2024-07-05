package com.cccjka.liuren.utils

import android.util.Log
import com.cccjka.liuren.api.api
import com.cccjka.liuren.bean.ResponseBean
import com.cccjka.liuren.bean.ResponseData
import com.google.gson.Gson
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

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

        suspend fun request(date: String): ResponseData = suspendCancellableCoroutine { it ->
            var responseData: ResponseData = ResponseData()

            val url = URL("https://www.36jxs.com/")
            val retrofit = Retrofit.Builder().baseUrl(url).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson())).build()
                .create(api::class.java)

            retrofit.getInfo(date).enqueue(object : retrofit2.Callback<ResponseBean<ResponseData>> {
                override fun onResponse(
                    call: retrofit2.Call<ResponseBean<ResponseData>>,
                    response: retrofit2.Response<ResponseBean<ResponseData>>
                ) {
                    responseData = CommonUtils.fromJson(CommonUtils.toJson(response.body()?.data))
                    it.resume(responseData)
                }

                override fun onFailure(
                    call: retrofit2.Call<ResponseBean<ResponseData>>,
                    t: Throwable
                ) {
                    Log.e("Retrofit", "请求数据失败")
                    it.resumeWithException(t)
                }
            })
//            return responseData
        }

        suspend fun getResult(date: String): ResponseData{
            val convertData = CommonUtils.fromJson<ResponseData>(Gson().toJson(request(date)))
            return convertData
        }
    }
}