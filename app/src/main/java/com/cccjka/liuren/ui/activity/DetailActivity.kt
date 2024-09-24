package com.cccjka.liuren.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cccjka.liuren.ui.DetailView
import com.cccjka.liuren.viewmodel.DetailViewModel

class DetailActivity : ComponentActivity()  {

    val viewModel: DetailViewModel = DetailViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        val day = intent.getStringExtra("day")
        viewModel.list.add(0, year!!)
        viewModel.list.add(1, month!!)
        viewModel.list.add(2, day!!)
        setContent {
            DetailView(viewModel.list)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun showDetail(){
//    DetailView()
}