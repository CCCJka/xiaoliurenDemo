package com.cccjka.liuren.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cccjka.liuren.R
import com.cccjka.liuren.bean.ResponseData
import com.cccjka.liuren.utils.CommonUtils
import com.cccjka.liuren.utils.DateUtils

@Composable
fun showdate(responseData: ResponseData){
    val backgroundPic = painterResource(R.drawable.paper);
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = backgroundPic, contentDescription = null,
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillHeight)
        Column (modifier = Modifier.padding(10.dp),){
            showCalendar(date = responseData)
        }
    }
}

@Composable
fun showCalendar(date: ResponseData){
    Column ( modifier = Modifier
        .verticalScroll(rememberScrollState())){
        TopInfo(lunarMonthAndDAy = date.LMonth + date.LDay)
        middleInfo(date = date)
        bottomInfo(date = date)
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
            modifier = Modifier.size(100.dp),
            contentDescription = null)
        Text(text = "${year}年",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp)
    }
}

@Composable
fun showLunarSeason(seasonName: String, monthName: String){
    Row {
        Text(text = "季度\n${seasonName}",
            fontSize = 20.sp)
        Text(text = "月名\n${monthName}",
            fontSize = 20.sp)
    }
}

@Composable
fun showSexagenaryCycle(year:String, month:String, day: String){
    Column {
        Text(text = "天干地支",
            Modifier.align(Alignment.CenterHorizontally))
        Text(text = "年：${year}",
            fontSize = 20.sp)
        Text(text = "月：${month}",
            fontSize = 20.sp)
        Text(text = "日：${day}",
            fontSize = 20.sp)
    }
}

@Composable
fun showShenwei(shenwei: String){
    showText(title = "神位", date = CommonUtils.splitChar(' ', shenwei, true, 5))
//    Column {
//        Text(text = "神位",
//            Modifier.align(Alignment.CenterHorizontally),
//            fontSize = 20.sp)
//        Text(text = CommonUtils.splitChar(' ', shenwei, true, 5))
//    }
}

@Composable
fun showTaishen(taiShen: String){
    showText(title = "胎神", date = CommonUtils.splitChar(',', taiShen, true, 5))
//    Column {
//        Text(text = "胎神",
//            Modifier.align(Alignment.CenterHorizontally),
//            fontSize = 20.sp)
//        Text(text = CommonUtils.splitChar(',', taiShen, true, 5))
//    }
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
        showShenwei(date.ShenWei)
        showYiOrJi(painter = yi, date.Yi)
        showYiOrJi(painter = ji,  date.Ji)
        showTaishen(date.Taishen)
    }
}

@Composable
fun bottomInfo(date: ResponseData){
    Row {
        showCurrentYear(date.LYear)
        showLunarSeason(date.LMonthName, date.MoonName)
        showSexagenaryCycle(date.TianGanDiZhiYear, date.TianGanDiZhiMonth, date.TianGanDiZhiDay)
        showText(title = "相冲", date = date.Chong)
        showText(title = "岁煞", date = date.SuiSha)
    }
}

@Composable
fun showText(title: String, date: String){
    Column {
        Text(text = title,
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp)
        Text(text = date)
    }
}