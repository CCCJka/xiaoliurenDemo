package com.cccjka.liuren.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cccjka.liuren.bean.ResponseData
import com.cccjka.liuren.navigation.LunarInfoNavigator
import com.cccjka.liuren.ui.showdate
import com.cccjka.liuren.ui.theme.LiuyaoTheme
import com.cccjka.liuren.utils.CommonUtils
import com.cccjka.liuren.utils.DateUtils
import com.cccjka.liuren.viewmodel.LunarInfoViewModel

class LunarInfoActivity : ComponentActivity() {

    val viewModel: LunarInfoViewModel = LunarInfoViewModel()

    val data: ResponseData = ResponseData()

    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel.request(DateUtils.returnYMD())

        super.onCreate(savedInstanceState)
        setContent {
            LiuyaoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    notifyView()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun notifyView() {
        val data by remember { viewModel.mutableStateInModel }
        showdate(data)
    }
}