package com.cccjka.liuren.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.cccjka.liuren.viewmodel.LunarInfoViewModel

@Composable
fun showdate(responseData: ResponseData){
    Column {
        showCalendar(date = responseData)
    }
}

@Composable
fun showCalendar(date: ResponseData){
    Column ( modifier = Modifier
        .verticalScroll(rememberScrollState())){
        Text(text = "国历：${date.GregorianDateTime}", fontSize = 30.sp)
        showLunarStr(date.LMonth + date.LDay)
        Text(text = "农历节日：${date.LJie}", fontSize = 30.sp)
        Text(text = "国历节日：${date.GJie}", fontSize = 30.sp)

        showTaishen(date.Taishen)
        showShenwei(date.ShenWei)
        val yi = painterResource(R.drawable.yi)
        val ji = painterResource(R.drawable.ji)
        showCurrentYear(date.LYear)
        showLunarSeason(date.LMonthName, date.MoonName)
        showSexagenaryCycle(date.TianGanDiZhiYear, date.TianGanDiZhiMonth, date.TianGanDiZhiDay)
        Row (modifier = Modifier.fillMaxSize()){
            showYiOrJi(painter = yi, Alignment.TopEnd, date.Yi)
            showYiOrJi(painter = ji, Alignment.TopStart, date.Ji)
        }
    }
}

@Composable
fun showYiOrJi(painter: Painter, align: Alignment, info: String){
    Column {
        Image(painter = painter,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(60.dp),
            alignment = align,
            contentDescription = null)
        Text(modifier = Modifier.align(Alignment.CenterHorizontally),
            text = CommonUtils.splitChar('.',info),
            fontSize = 20.sp)
    }
}

@Composable
fun showLunarStr(info: String){
    Text(text = info, fontSize = 30.sp)
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
    Column {
        Text(text = "神位",
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp)
        Text(text = CommonUtils.splitChar(' ', shenwei))
    }
}

@Composable
fun showTaishen(taiShen: String){
    Column {
        Text(text = "胎神",
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp)
        Text(text = CommonUtils.splitChar(',', taiShen))
    }
}