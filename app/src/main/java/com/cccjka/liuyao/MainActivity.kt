package com.cccjka.liuyao

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cccjka.liuyao.ui.theme.LiuyaoTheme
import com.cccjka.liuyao.utils.DateUtils

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            LiuyaoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview()
                }
            }
        }
        val result = getMonth()
    }
}

fun getMonth(): String{
    val monthResult =  when (DateUtils.getMonth()) {
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
    val dayResult = getDay(
                        when (monthResult){
                            "大安" -> 1
                            "留连" -> 2
                            "速喜" -> 3
                            "赤口" -> 4
                            "小吉" -> 5
                            "空亡" -> 6
                            else -> 0
                    })
    return "$monthResult-$dayResult"
}

fun getDay(monthResult: Int): String{
    return when (DateUtils.getDay()){
        1 -> "大安"
        2 -> "留连"
        3 -> "速喜"
        4 -> "赤口"
        5 -> "小吉"
        6 -> "空亡"
        else -> { "unkonwn" }
    }
}

@Composable
fun AllView(){
    Column (verticalArrangement = Arrangement.Center) {
        ShowResult(result = stringResource(id = R.string.chikou_daan))
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
        AllView()
    }
}