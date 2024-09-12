package com.cccjka.liuren.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.cccjka.liuren.R
import com.cccjka.liuren.bean.ResponseData
import com.cccjka.liuren.ui.activity.DetailActivity
import com.cccjka.liuren.utils.CommonUtils
import com.cccjka.liuren.utils.DateUtils

@Composable
fun showdate(gender: String, responseData: ResponseData){
    val backgroundPic = painterResource(R.drawable.paper);
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = backgroundPic, contentDescription = null,
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillHeight)
        Column (modifier = Modifier.padding(10.dp),){
            showCalendar(gender, date = responseData)
        }
    }
}

@Composable
fun showCalendar(gender: String, date: ResponseData){
    Column ( modifier = Modifier
        .verticalScroll(rememberScrollState())){
        TopInfo(lunarMonthAndDAy = date.LMonth + date.LDay)
        middleInfo(date = date)
        bottomInfo(gender, date = date)
    }
}

@Composable
fun showYiOrJi(painter: Painter, info: String){
    Column {
        Image(painter = painter,
            modifier = Modifier
                .size(40.dp)
                .align(alignment = Alignment.CenterHorizontally),
            contentDescription = null)
        Text(modifier = Modifier.align(Alignment.CenterHorizontally),
            text = CommonUtils.splitChar('.',info, true, 4),
            fontSize = 20.sp)
    }
}

@Composable
fun showCurrentYear(year: String){
    val animalId = CommonUtils.getCurrentYearAnimal(year);
    val painter = painterResource(id = animalId);
    Column {
        Image(painter = painter,
            modifier = Modifier.size(80.dp),
            contentDescription = null)
        Text(text = "${year}年",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp)
    }
}

@Composable
fun showSexagenaryCycle(year:String, month:String, day: String){
    Column {
        Text(text = "天干地支",
            Modifier.align(Alignment.CenterHorizontally))
        Text(text = "年：${year}",
            fontSize = 15.sp)
        Text(text = "月：${month}",
            fontSize = 15.sp)
        Text(text = "日：${day}",
            fontSize = 15.sp)
    }
}

@Composable
fun TopInfo(lunarMonthAndDAy: String){
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = DateUtils.getCurrentYear(),
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp))
        Text(text = lunarMonthAndDAy,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 20.dp))
    }
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center){
        Text(text = DateUtils.getCurrentMonth() + "月" + DateUtils.getCurrentday() + "日",
            fontSize = 100.sp,
            modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Composable
fun middleInfo(date: ResponseData){
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = DateUtils.getCurrentDayOfWeek(),
            fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.Center))
    }
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceAround){
        val yi = painterResource(R.drawable.yi)
        val ji = painterResource(R.drawable.ji)
        showText(title = "神位", date = CommonUtils.splitChar(' ', date.ShenWei, true, 5))
        showYiOrJi(painter = yi, date.Yi)
        showYiOrJi(painter = ji,  date.Ji)
        showText(title = "胎神", date = CommonUtils.splitChar(',', date.Taishen, true, 5))
    }
}

@Composable
fun bottomInfo(gender: String, date: ResponseData){
    Row (Modifier.fillMaxWidth()){
        showCurrentYear(date.LYear)
        showSexagenaryCycle(date.TianGanDiZhiYear, date.TianGanDiZhiMonth, date.TianGanDiZhiDay)
        Column {
            showText(title = "相冲", date = date.Chong)
            showText(title = "岁煞", date = date.SuiSha)
        }
        liurenResult(gender, "")
    }
}

@Composable
fun  liurenResult(gender: String, date: String){
    val context = LocalContext.current
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra("gendar", gender)
    intent.putExtra("data", date)
    Button(onClick = { CommonUtils.navigatorActivity(context, intent) }) {
        Text(text = stringResource(id = R.string.show_detail))
    }
}

@Composable
fun showText(title: String, date: String) {
    Column {
        Text(
            text = title,
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp
        )
        Text(text = date,
            Modifier.align(Alignment.CenterHorizontally))
    }
}