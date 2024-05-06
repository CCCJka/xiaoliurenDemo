package com.cccjka.liuren.bean

class ResponseBean<T> {
   var code: Int? = 0
   var msg: String? = ""
   var time: Long? = 0L
   var data: T? = null
}