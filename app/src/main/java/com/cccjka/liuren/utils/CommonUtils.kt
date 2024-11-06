package com.cccjka.liuren.utils


import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.cccjka.liuren.R
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

object CommonUtils {

    var dayClass = ""
    var timeClass = ""


    /**
    * 分割字符串并返回拼接的字符串
     * @param symbol 分割的符号
     * @param info 需要分割的字符串
     * @param needControlLength  是否需要判断长度
     * @param length 分割后字符串的长度,需要needControlLength为true
     * @return 返回分割后的拼接字符串
    * */
    fun splitChar(symbol: Char, info: String, needControlLength: Boolean, length: Int): String{
        val infobyte = info.split(symbol)
        var stringBuffer = StringBuffer();
        for (str: String in infobyte){
            if (str != ""){
                if(needControlLength){
                    stringBuffer.append(isStringLongerThan5Char(str, length) + "\n")
                }else{
                    stringBuffer.append(str + "\n")
                }
            }
        }
        return stringBuffer.toString();
    }

    fun isStringLongerThan5Char(str: String, subLength: Int): String{
        val length = str.length;
        if (length > subLength){    //如果传入的字符串小于参数的截取大小，那么返回传入的字符串
            var stringBuffer = StringBuffer()
            for(index in 0..length step subLength){
                if (index+subLength >= length) {
                    stringBuffer.append(str.substring(index until length) + "\n")
                    break
                }else{
                    stringBuffer.append(str.substring(index until index+subLength) + "\n")
                }
            }
            return stringBuffer.toString().trim()
        }
        return str
    }

    fun navigatorActivity(context: Context, intent: Intent){
        context.startActivity(intent)
    }

    /**
    * 返回当前的年份所属的生肖图片
    * */
    fun getCurrentYearAnimal(info: String): Int{
        when(info){
            "鼠" -> return R.drawable.rat
            "牛" -> return R.drawable.ox
            "虎" -> return R.drawable.tiger
            "兔" -> return R.drawable.rabbit
            "龙" -> return R.drawable.loong
            "蛇" -> return R.drawable.snake
            "马" -> return R.drawable.horse
            "羊" -> return R.drawable.goat
            "猴" -> return R.drawable.monkey
            "鸡" -> return R.drawable.rooster
            "狗" -> return R.drawable.dog
            "猪" -> return R.drawable.pig
            else -> return R.drawable.unknown
        }
    }

    /**十二值日*/
    fun getTwelveDay(info: String): Int{
        when(info){
            "危" -> return R.string.wei_day
            "开" -> return R.string.kai_day
            "成" -> return R.string.cheng_day
            "闭" -> return R.string.bi_day
            "建" -> return R.string.jian_day
            "收" -> return R.string.shou_day
            "满" -> return R.string.man_day
            "平" -> return R.string.ping_day
            "定" -> return R.string.ding_day
            "执" -> return R.string.zhi_day
            "破" -> return R.string.po_day
            "除" -> return R.string.chu_day
            else -> return 0
        }
    }

    /**
    * 获取结果
     * @param result 获取到的结果
     * @param startAs 0:正常起课  1:重阴起课  2:重阳起课
     * @return 获取到的结果
    * */
    fun getResult(result: Int, startAs: Int): String{
        if (startAs == 0){
            return when (result) {
                1 -> "大安"
                2 -> "留连"
                3 -> "速喜"
                4 -> "赤口"
                5 -> "小吉"
                6 -> "空亡"
                0 -> "空亡"
                else -> {
                    "unkonwn"
                }
            }
        } else if (startAs == 1){
            return when (result) {
                1 -> "留连"
                2 -> "速喜"
                3 -> "赤口"
                4 -> "小吉"
                5 -> "空亡"
                6 -> "大安"
                0 -> "大安"
                else -> {
                    "unkonwn"
                }
            }
        } else if (startAs == 2){
            return when (result) {
                1 -> "小吉"
                2 -> "空亡"
                3 -> "大安"
                4 -> "留连"
                5 -> "速喜"
                6 -> "赤口"
                0 -> "赤口"
                else -> {
                    "unkonwn"
                }
            }
        }
        return "unkonwn"
    }

    /**
    * 获取当前小时
    * */
    fun getTime(): Int{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH")
        return current.format(formatter).toInt()
    }

    /**
    * 获取十二时辰
    * */
    fun getCurrentTime(hour: Int): Int{
        return when(hour){
            23 -> 1
            in 1..2 -> 2
            in 3..4 -> 3
            in 5..6 -> 4
            in 7..8 -> 5
            in 9..10 -> 6
            in 11..12 -> 7
            in 13..14 -> 8
            in 15..16 -> 9
            in 17..18 -> 10
            in 19..20 -> 11
            in 21..22 -> 12
            else -> { 1 }
        }
    }

    /**
     * 获取根据日时获取的结果
     * */
    fun getResult(startAs: Int): String{
        val lunar = Lunar()
        val getday = (lunar.lunarMonth - 1) + lunar.lunarDay
        val day = getResult(getday % 6, startAs)
        dayClass = day
        val getHour = (lunar.lunarMonth - 1)  + (lunar.lunarDay - 1) + getCurrentTime(getTime())
        val hour = getResult(getHour % 6, 0)
        timeClass = hour
        return when(day){
            "大安" -> {
                return when(hour){
                    "留连" -> DAAN.LIULIAN.hour
                    "速喜" -> DAAN.SUXI.hour
                    "赤口" -> DAAN.CHIKOU.hour
                    "小吉" -> DAAN.XIAOJI.hour
                    "空亡" -> DAAN.KONGWANG.hour
                    else -> { "双大安" }
                }
            }
            "留连" -> {
                return when(hour){
                    "大安" -> LIULIAN.DAAN.hour
                    "速喜" -> LIULIAN.SUXI.hour
                    "赤口" -> LIULIAN.CHIKOU.hour
                    "小吉" -> LIULIAN.XIAOJI.hour
                    "空亡" -> LIULIAN.KONGWANG.hour
                    else -> { "双留连" }
                }
            }
            "速喜" -> {
                return when(hour){
                    "大安" -> SUXI.DAAN.hour
                    "留连" -> SUXI.LIULIAN.hour
                    "赤口" -> SUXI.CHIKOU.hour
                    "小吉" -> SUXI.XIAOJI.hour
                    "空亡" -> SUXI.KONGWANG.hour
                    else -> { "双速喜" }
                }
            }
            "赤口" -> {
                return when(hour){
                    "大安" -> CHIKOU.DAAN.hour
                    "留连" -> CHIKOU.LIULIAN.hour
                    "速喜" -> CHIKOU.SUXI.hour
                    "小吉" -> CHIKOU.XIAOJI.hour
                    "空亡" -> CHIKOU.KONGWANG.hour
                    else -> { "双赤口" }
                }
            }
            "小吉" -> {
                return when(hour){
                    "大安" -> XIAOJI.DAAN.hour
                    "留连" -> XIAOJI.LIULIAN.hour
                    "速喜" -> XIAOJI.SUXI.hour
                    "赤口" -> XIAOJI.CHIKOU.hour
                    "空亡" -> XIAOJI.KONGWANG.hour
                    else -> { "双小吉" }
                }
            }
            "空亡" -> {
                return when(hour){
                    "大安" -> KONGWANG.DAAN.hour
                    "留连" -> KONGWANG.LIULIAN.hour
                    "速喜" -> KONGWANG.SUXI.hour
                    "赤口" -> KONGWANG.CHIKOU.hour
                    "小吉" -> KONGWANG.XIAOJI.hour
                    else -> { "双空亡" }
                }
            }
            else -> { "unknown" }
        }
    }

    fun returnClass(context: Context, list: List<String>): ArrayList<String>{
        val list = arrayListOf("")
        var str = ""
        str = when (dayClass){
            "大安" -> context.getString(R.string.daan_info)
            "留连" -> context.getString(R.string.liulian_info)
            "速喜" -> context.getString(R.string.suxi_info)
            "赤口" -> context.getString(R.string.chikou_info)
            "小吉" -> context.getString(R.string.xiaoji_info)
            "空亡" -> context.getString(R.string.kongwang_info)
            else ->  ""
        }
        list.add(0, str)
        str = when (timeClass){
            "大安" -> context.getString(R.string.daan_wuxing)
            "留连" -> context.getString(R.string.liulian_wuxing)
            "速喜" -> context.getString(R.string.suxi_wuxing)
            "赤口" -> context.getString(R.string.chikou_wuxing)
            "小吉" -> context.getString(R.string.xiaoji_wuxing)
            "空亡" -> context.getString(R.string.kongwang_wuxing)
            else ->  ""
        }
        list.add(1, str)
        if (list[1].startsWith("甲") && list[2].startsWith("乙")){
            str = context.getString(R.string.tiangan_jia_yi)
            list.add(2, str)
        } else if (list[1].startsWith("乙") && list[2].startsWith("庚")){
            str = context.getString(R.string.tiangan_yi_geng)
            list.add(2, str)
        } else if (list[1].startsWith("丙") && list[2].startsWith("辛")){
            str = context.getString(R.string.tiangan_bing_xin)
            list.add(2, str)
        } else if (list[1].startsWith("丁") && list[2].startsWith("壬")){
            str = context.getString(R.string.tiangan_ding_ren)
            list.add(2, str)
        } else {
            list.add(2, "")
        }
        if (dayClass == "大安" && timeClass == "留连"){
            str = context.getString(R.string.liushen_daan_xiaoji);
            list.add(3, str)
        } else if (dayClass == "大安" && timeClass == "小吉"){
            str = context.getString(R.string.liushen_daan_xiaoji)
            list.add(3, str)
        } else if (dayClass == "赤口" && timeClass== "空亡"){
            str = context.getString(R.string.liushen_chikou_kongwang)
            list.add(3, str)
        } else if (dayClass == "速喜"){
            str = context.getString(R.string.liushen_suxi)
            list.add(3, str)
        } else {
            list.add(3, "")
        }
        return list
    }

    fun startClass(gender: String){
        if (gender == "man"){
            getResult(1, 2)
        }else{
            getResult(1, 1)
        }
    }

    fun <T>toJson(date: T): String{
        return Gson().toJson(date)
    }

    inline fun <reified T>fromJson(json: String): T{
        val result = Gson().fromJson(json, T::class.java)
        return result
    }
}

enum class DAAN(val hour:String){
    LIULIAN("大安加留连\n 办事不周全\n 失物西北去\n 婚姻晚几天"),
    SUXI("大安加速喜\n 事事自己起\n 失物当日见\n 婚姻自己提"),
    CHIKOU("大安加赤口\n 办事不顺手\n 失物不用找\n 婚姻两分手"),
    XIAOJI("大安加小吉\n 事事从己及\n 失物不出门\n 婚姻成就地"),
    KONGWANG("大安加空亡\n 病人要上床\n 失物无踪影\n 事事不顺情");
}

enum class LIULIAN(val hour: String){
    DAAN("留连加大安\n 办事两分张\n 婚姻有喜事\n 先苦后来甜"),
    SUXI("留连加速喜\n 事事由自己\n 婚姻有成意\n 失物三天里"),
    CHIKOU("留连加赤口\n 病者死人口\n 失物准丢失\n 婚姻两分手"),
    XIAOJI("留连加小吉\n 事事不用提\n 失物东南去\n 病者出人齐"),
    KONGWANG("留连加空亡\n 病人准死亡\n 失物不见面\n 婚姻两分张")
}

enum class SUXI(val hour: String){
    DAAN("速喜加大安\n 事事都平安\n 姻姻成全了\n 占病都相安"),
    LIULIAN("速喜加留连\n 婚姻不可言\n 失物无信息\n 病人有仙缘"),
    CHIKOU("速喜加赤口\n 自己往外走\n 失物往正北\n 婚姻得勤走"),
    XIAOJI("速喜加小吉\n 婚姻有人提\n 病人当天好\n 时物在家里"),
    KONGWANG("速喜加空亡\n 婚姻有分张\n 病者积极治\n 失物不久见")
}

enum class CHIKOU(val hour: String){
    DAAN("赤口加大安\n 办事险和难\n 失物东北找\n 婚姻指定难"),
    LIULIAN("赤口加留连\n 办事有困难\n 行人在外走\n 失物不回还"),
    SUXI("赤口加速喜\n 婚姻在自己\n 失物有着落\n 办事官事起"),
    XIAOJI("赤口加小吉\n 办事自己提\n 婚姻不能成\n 失物无信息"),
    KONGWANG("赤口加空亡\n 无病也上床\n 失物不用找\n 婚姻不能成")
}

enum class XIAOJI(val hour: String){
    DAAN("小吉加大安\n 事事两周全\n 婚姻当日定\n 失物自己损"),
    LIULIAN("小吉加留连\n 事事有反还\n 婚姻有人破\n 失物上西南"),
    SUXI("小吉加速喜\n 事事从头起\n 婚姻能成就\n 失物在院里"),
    CHIKOU("小吉加赤口\n 办事往外走\n 婚姻有难处\n 失物丢了手"),
    KONGWANG("小吉加空亡\n 病人不妥当\n 失物正东找\n 婚姻再想想")
}

enum class KONGWANG(val hour: String){
    DAAN("空亡加大安\n 事事不周全\n 婚姻从和好\n 失物反复间"),
    LIULIAN("空亡加留连\n 办事处处难\n 婚姻重新定\n 失物永不还"),
    SUXI("空亡加速喜\n 事事怨自己\n 婚姻有一定\n 失物在家里"),
    CHIKOU("空亡加赤口\n 办事官非有\n 婚姻难定准\n 失物往远走"),
    XIAOJI("空亡加小吉\n 事事有猜疑\n 婚姻有喜事\n 失物回家里")
}