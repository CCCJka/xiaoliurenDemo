package com.cccjka.liuyao

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.widget.Toast
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
import com.cccjka.liuyao.api.api
import com.cccjka.liuyao.bean.ResponseBean
import com.cccjka.liuyao.bean.ResponseData
import com.cccjka.liuyao.ui.theme.LiuyaoTheme
import com.cccjka.liuyao.utils.CommonUtils
import com.cccjka.liuyao.utils.Lunar
import com.cccjka.liuyao.utils.OkHttpClient
import com.cccjka.liuyao.viewmodel.MainViewModel
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.URL


class MainActivity : ComponentActivity() {

    val lunar = Lunar()
    val mainViewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        val getday = (lunar.lunarMonth - 1) + lunar.lunarDay
        val day = CommonUtils.getResult(getday % 6)
        val getHour = (lunar.lunarMonth - 1)  + (lunar.lunarDay - 1) + CommonUtils.getCurrentTime(CommonUtils.getTime())
        val hour = CommonUtils.getResult(getHour % 6)
        val result = CommonUtils.getResult(day,hour)
        val str = mainViewModel.lunarInfo(CommonUtils.returnYMD())
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

@Composable
fun ShowResult(result: String){
    Column {
        Text(text = "日月时：\n $result")
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LiuyaoTheme {
//        AllView(result = result)
    }
}
