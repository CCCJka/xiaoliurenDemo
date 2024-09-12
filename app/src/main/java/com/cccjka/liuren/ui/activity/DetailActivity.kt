package com.cccjka.liuren.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import com.cccjka.liuren.ui.theme.LiuyaoTheme
import com.cccjka.liuren.viewmodel.DetailViewModel

class DetailActivity : ComponentActivity()  {

    val viewModel: DetailViewModel = DetailViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            showDetail(gender = "man", date = "")
        }
    }

}

@Composable
fun showDetail(gender: String, date: String){
    Text(text = "test")
}