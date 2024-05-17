package com.cccjka.liuren.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cccjka.liuren.viewmodel.LunarInfoViewModel

@Composable
fun showdate(viewmodel: LunarInfoViewModel){
    val dataUiState by viewmodel.uiState.collectAsState()

    Column {
        Text(text = "农历 ${dataUiState.LunarDateTime}")
        Text(text = "宜：${dataUiState.Yi}")
        Text(text = "忌：${dataUiState.Ji}")
    }
}