package com.cccjka.liuyao.bean

import java.io.Serializable

class ResponseBean<T> {
   var code: Int? = 0
   var msg: String? = ""
   var time: Long? = 0L
   var data: T? = null
}