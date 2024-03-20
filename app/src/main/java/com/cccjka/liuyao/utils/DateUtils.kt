package com.cccjka.liuyao.utils

import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    fun getMonth(): Int {
        val df = SimpleDateFormat("MM") //返回月份
        val num = df.format(Date()).toInt()
        return num
    }

    fun getDay(): Int {
        val df = SimpleDateFormat("dd") //返回当天为几号
        val num = df.format(Date()).toInt()
        return num
    }

    fun getHours(): Int {
        val df = SimpleDateFormat("HH") //返回当前小时
        return df.format(Date()).toInt()
    }
}