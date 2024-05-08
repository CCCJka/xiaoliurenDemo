package com.cccjka.liuren.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import com.cccjka.liuren.bean.ResponseData
import com.cccjka.liuren.navigation.LunarInfoNavigator
import com.cccjka.liuren.ui.theme.LiuyaoTheme
import com.cccjka.liuren.utils.CommonUtils
import com.cccjka.liuren.utils.OkHttpClient
import com.cccjka.liuren.viewmodel.LunarInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait

class LunarInfoActivity : ComponentActivity(), LunarInfoNavigator {


    val viewModel: LunarInfoViewModel = LunarInfoViewModel()

    var responseData: ResponseData = ResponseData()


    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel.setNavigator(this)
        viewModel.request(CommonUtils.returnYMD())

        super.onCreate(savedInstanceState)
        setContent {
            LiuyaoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    showCalendar(responseData)
                }
            }
        }
    }

    override fun notifyView() {
        viewModel.getResponseData()
    }


}


@Composable
fun showCalendar(responseData: ResponseData){
    Column {
        showdate(responseData)
    }
}

@Composable
fun showdate(responseData: ResponseData){
    Column {
        Text(text = "农历 ${responseData.LunarDateTime}")
        Text(text = "宜：${responseData.Yi}")
        Text(text = "忌：${responseData.Ji}")
    }
}