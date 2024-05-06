package com.cccjka.liuren.api

import com.cccjka.liuren.bean.ResponseBean
import com.cccjka.liuren.bean.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface api {

    @GET("api/Commonweal/almanac/")
    fun getInfo(@Query("sun")date: String): Call<ResponseBean<ResponseData>>

}