package com.cccjka.liuyao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cccjka.liuyao.ui.theme.LiuyaoTheme
import com.cccjka.liuyao.utils.DateUtils
import com.cccjka.liuyao.utils.Lunar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainActivity : ComponentActivity() {

    val lunar = Lunar()

    override fun onCreate(savedInstanceState: Bundle?) {

        val month = getResult(lunar.lunarMonth)
        val getday = (lunar.lunarMonth - 1) + lunar.lunarDay
        val day = getResult(getday % 6)
        val getHour = (lunar.lunarMonth - 1)  + (lunar.lunarDay - 1) + getCurrentTime(getTime())
        val hour = getResult(getHour % 6)
        val result = when(day){
            "大安" -> {
                when(hour){
                    "留连" -> DAAN.LIULIAN.hour
                    "速喜" -> DAAN.SUXI.hour
                    "赤口" -> DAAN.CHIKOU.hour
                    "小吉" -> DAAN.XIAOJI.hour
                    "空亡" -> DAAN.KONGWANG.hour
                    else -> { "双大安" }
                }
            }
            "留连" -> {
                when(hour){
                    "大安" -> LIULIAN.DAAN.hour
                    "速喜" -> LIULIAN.SUXI.hour
                    "赤口" -> LIULIAN.CHIKOU.hour
                    "小吉" -> LIULIAN.XIAOJI.hour
                    "空亡" -> LIULIAN.KONGWANG.hour
                    else -> { "双留连" }
                }
            }
            "速喜" -> {
                when(hour){
                    "大安" -> SUXI.DAAN.hour
                    "留连" -> SUXI.LIULIAN.hour
                    "赤口" -> SUXI.CHIKOU.hour
                    "小吉" -> SUXI.XIAOJI.hour
                    "空亡" -> SUXI.KONGWANG.hour
                    else -> { "双速喜" }
                }
            }
            "赤口" -> {
                when(hour){
                    "大安" -> CHIKOU.DAAN.hour
                    "留连" -> CHIKOU.LIULIAN.hour
                    "速喜" -> CHIKOU.SUXI.hour
                    "小吉" -> CHIKOU.XIAOJI.hour
                    "空亡" -> CHIKOU.KONGWANG.hour
                    else -> { "双赤口" }
                }
            }
            "小吉" -> {
                when(hour){
                    "大安" -> XIAOJI.DAAN.hour
                    "留连" -> XIAOJI.LIULIAN.hour
                    "速喜" -> XIAOJI.SUXI.hour
                    "赤口" -> XIAOJI.CHIKOU.hour
                    "空亡" -> XIAOJI.KONGWANG.hour
                    else -> { "双小吉" }
                }
            }
            "空亡" -> {
                when(hour){
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

        super.onCreate(savedInstanceState)
        setContent {
            LiuyaoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    GreetingPreview()
                    AllView(result = result)
                }
            }
        }

    }
}

fun getResult(result: Int): String{
    return when (result) {
        1 -> "大安"
        2 -> "留连"
        3 -> "速喜"
        4 -> "赤口"
        5 -> "小吉"
        6 -> "空亡"
        else -> {
            "unkonwn"
        }
    }
}

fun getTime(): Int{
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH")
    return current.format(formatter).toInt()
}

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

@Composable
fun AllView(result: String){
    Column (verticalArrangement = Arrangement.Center) {
        ShowResult(result = result)
    }
}

@Composable
fun ShowResult(result: String){
    Column {
        Text(text = "日月时：\n $result")
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LiuyaoTheme {
//        AllView()
    }
}

enum class DAAN(val hour:String){
    LIULIAN("大安加留连，办事不周全，失物西北去，婚姻晚几天。"),
    SUXI("大安加速喜，事事自己起，失物当日见，婚姻自己提。"),
    CHIKOU("大安加赤口，办事不顺手，失物不用找，婚姻两分手。"),
    XIAOJI("大安加小吉，事事从己及，失物不出门，婚姻成就地。"),
    KONGWANG("大安加空亡，病人要上床，失物无踪影，事事不顺情。");
}

enum class LIULIAN(val hour: String){
    DAAN("留连加大安，办事两分张，婚姻有喜事，先苦后来甜。"),
    SUXI("留连加速喜，事事由自己，婚姻有成意，失物三天里。"),
    CHIKOU("留连加赤口，病者死人口，失物准丢失，婚姻两分手。"),
    XIAOJI("留连加小吉，事事不用提，失物东南去，病者出人齐。"),
    KONGWANG("留连加空亡，病人准死亡，失物不见面，婚姻两分张。")
}

enum class SUXI(val hour: String){
    DAAN("速喜加大安，事事都平安，姻姻成全了，占病都相安。"),
    LIULIAN("速喜加留连，婚姻不可言，失物无信息，病人有仙缘。"),
    CHIKOU("速喜加赤口，自己往外走，失物往正北，婚姻得勤走。"),
    XIAOJI("速喜加小吉，婚姻有人提，病人当天好，时物在家里。"),
    KONGWANG("速喜加空亡，婚姻有分张，病者积极治，失物不久见。")
}

enum class CHIKOU(val hour: String){
    DAAN("赤口加大安，办事险和难，失物东北找，婚姻指定难。"),
    LIULIAN("赤口加留连，办事有困难，行人在外走，失物不回还。"),
    SUXI("赤口加速喜，婚姻在自己，失物有着落，办事官事起。"),
    XIAOJI("赤口加小吉，办事自己提，婚姻不能成，失物无信息。"),
    KONGWANG("赤口加空亡，无病也上床，失物不用找，婚姻不能成。")
}

enum class XIAOJI(val hour: String){
    DAAN("小吉加大安，事事两周全，婚姻当日定，失物自己损。"),
    LIULIAN("小吉加留连，事事有反还，婚姻有人破，失物上西南。"),
    SUXI("小吉加速喜，事事从头起，婚姻能成就，失物在院里。"),
    CHIKOU("小吉加赤口，办事往外走，婚姻有难处，失物丢了手。"),
    KONGWANG("小吉加空亡，病人不妥当，失物正东找，婚姻再想想。")
}

enum class KONGWANG(val hour: String){
    DAAN("空亡加大安，事事不周全，婚姻从和好，失物反复间。"),
    LIULIAN("空亡加留连，办事处处难，婚姻重新定，失物永不还。"),
    SUXI("空亡加速喜，事事怨自己，婚姻有一定，失物在家里。"),
    CHIKOU("空亡加赤口，办事官非有，婚姻难定准，失物往远走。"),
    XIAOJI("空亡加小吉，事事有猜疑，婚姻有喜事，失物回家里。")
}