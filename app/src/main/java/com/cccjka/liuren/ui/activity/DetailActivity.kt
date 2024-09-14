package com.cccjka.liuren.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.cccjka.liuren.R
import com.cccjka.liuren.ui.DetailView
import com.cccjka.liuren.ui.theme.LiuyaoTheme
import com.cccjka.liuren.viewmodel.DetailViewModel

class DetailActivity : ComponentActivity()  {

    val viewModel: DetailViewModel = DetailViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            showDetail()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun showDetail(){
    DetailView()
}