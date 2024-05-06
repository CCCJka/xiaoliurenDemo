package com.cccjka.liuren.ui.activity

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cccjka.liuren.ui.theme.LiuyaoTheme
import com.cccjka.liuren.utils.CommonUtils
import com.cccjka.liuren.utils.Lunar
import com.cccjka.liuren.viewmodel.MainViewModel


class MainActivity : ComponentActivity() {

    val lunar = Lunar()
    val mainViewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        val getday = (lunar.lunarMonth - 1) + lunar.lunarDay
        val day = CommonUtils.getResult(getday % 6)
        val getHour = (lunar.lunarMonth - 1)  + (lunar.lunarDay - 1) + CommonUtils.getCurrentTime(CommonUtils.getTime())
        val hour = CommonUtils.getResult(getHour % 6)
        val result = CommonUtils.getResult(day,hour)
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


@Composable
fun AllView(result: String){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShowResult(result = result)
    }
}

//@Composable
//fun showCalendar(){
//
//}

@Composable
fun ShowResult(result: String){
    Column {
        Text(text = "日时速断：\n $result")
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LiuyaoTheme {
//        AllView(result = result)
    }
}
