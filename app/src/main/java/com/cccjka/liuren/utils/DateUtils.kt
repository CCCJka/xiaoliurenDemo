package com.cccjka.liuren.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Calendar

object DateUtils {

    val calendar = Calendar.getInstance()

    fun getCurrentYear(): String{
        val year = LocalDate.now().year
        return year.toString()
    }

    fun getCurrentMonth(): String{
        val month = LocalDate.now().month
        return month.value.toString()
    }

    fun getCurrentday(): String{
        val day = LocalDate.now().dayOfMonth
        return day.toString()
    }

    fun getCurrentDayOfWeek(): String{
        val dayOfWeek = LocalDate.now().dayOfWeek
        val dayOfWeekForMat = when(dayOfWeek){
            DayOfWeek.MONDAY -> "星期一"
            DayOfWeek.TUESDAY -> "星期二"
            DayOfWeek.WEDNESDAY -> "星期三"
            DayOfWeek.THURSDAY -> "星期四"
            DayOfWeek.FRIDAY -> "星期五"
            DayOfWeek.SATURDAY -> "星期六"
            DayOfWeek.SUNDAY -> "星期日"
            else -> ""
        }
        return dayOfWeekForMat;
    }

    /**
     * 返回年月日
     */
    fun returnYMD(): String{
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return "$year-$month-$day"
    }
}