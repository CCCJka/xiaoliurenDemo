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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cccjka.liuren.ui.showdate
import com.cccjka.liuren.ui.theme.LiuyaoTheme
import com.cccjka.liuren.utils.CommonUtils
import com.cccjka.liuren.utils.DateUtils
import com.cccjka.liuren.utils.Lunar
import com.cccjka.liuren.viewmodel.MainViewModel


class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel = MainViewModel()

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
