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
import com.cccjka.liuren.bean.ResponseData
import com.cccjka.liuren.ui.theme.LiuyaoTheme
import com.cccjka.liuren.utils.CommonUtils
import com.cccjka.liuren.viewmodel.LunarInfoViewModel

class LunarInfoActivity : ComponentActivity(){

    val viewModel: LunarInfoViewModel = LunarInfoViewModel()

    var responseData: ResponseData = ResponseData()

    override fun onCreate(savedInstanceState: Bundle?) {

//        responseData = viewModel.lunarInfo(CommonUtils.returnYMD())

        super.onCreate(savedInstanceState)
        setContent {
            LiuyaoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    showCalendar()
                }
            }
        }
    }
}


@Preview
@Composable
fun showCalendar(){
    Column {
        showdate()

    }
}

@Composable
fun showdate(){
    Column {
//        Text(text = "日期" + )
    }
}