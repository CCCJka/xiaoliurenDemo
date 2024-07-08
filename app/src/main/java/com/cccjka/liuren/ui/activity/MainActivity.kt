package com.cccjka.liuren.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
        val activity = LunarInfoActivity()
        setContent {
            LiuyaoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    GreetingPreview()
                    AllView(result = result, click = { Navigation(activity) })
                }
            }
        }
        Navigation(activity)
    }
    fun Navigation(activity: Activity){
        startActivity(Intent(this, activity::class.java))
    }
}


@Composable
fun AllView(result: String, click: () -> Unit){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShowResult(result = result)
        Button(onClick = click) {
            Text(text = "跳转")
        }
    }
}

@Composable
fun ShowResult(result: String){
    Column {
        Text(text = result)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LiuyaoTheme {
//        AllView(result = result)
    }
}
