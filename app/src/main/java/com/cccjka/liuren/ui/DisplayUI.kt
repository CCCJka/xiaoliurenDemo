package com.cccjka.liuren.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.cccjka.liuren.utils.Lunar

@Composable
fun showdata(responseData: ResponseData){
    val backgroundPic = painterResource(R.drawable.paper)
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = backgroundPic, contentDescription = null,
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillHeight)
        Column (modifier = Modifier.padding(10.dp),){
            showCalendar(data = responseData)
        }
    }
}

@Composable
fun showCalendar(data: ResponseData){
    Column ( modifier = Modifier
        .verticalScroll(rememberScrollState())){
        TopInfo(lunarMonthAndDAy = data.LMonth + data.LDay)
        middleInfo(data = data)
        bottomInfo(data = data)
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
fun middleInfo(data: ResponseData){
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
        showText(title = "神位", data = CommonUtils.splitChar(' ', data.ShenWei, true, 5))
        showYiOrJi(painter = yi, data.Yi)
        showYiOrJi(painter = ji,  data.Ji)
        showText(title = "胎神", data = CommonUtils.splitChar(',', data.Taishen, true, 5))
    }
}

@Composable
fun bottomInfo(data: ResponseData){
    Row (
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        showCurrentYear(data.LYear)
        showSexagenaryCycle(data.TianGanDiZhiYear, data.TianGanDiZhiMonth, data.TianGanDiZhiDay)
        xiangChongSuiSha(data)
        liurenResult()
    }
}

@Composable
fun xiangChongSuiSha(data: ResponseData){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        showText(title = "相冲", data = data.Chong)
        showText(title = "岁煞", data = data.SuiSha)
    }
}

@Composable
fun  liurenResult(){
    val context = LocalContext.current
    val intent = Intent(context, DetailActivity::class.java)
    Text(text = CommonUtils.getResult(), Modifier.clickable {
        CommonUtils.navigatorActivity(context, intent)
    })
}

@Composable
fun showText(title: String, data: String) {
    Column {
        Text(
            text = title,
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp
        )
        Text(text = data,
            Modifier.align(Alignment.CenterHorizontally))
    }
}

/*****     MainActivity UI End       *******/

@Composable
fun DetailView(){
    val backgroundPic = painterResource(R.drawable.paper)
    val lunar = Lunar()
    val lunarDay = lunar.lunarDay
    val getHour = CommonUtils.getCurrentTime(CommonUtils.getTime())
    val totalCount = lunarDay + getHour
    Image(painter = backgroundPic, contentDescription = null)
    Row {
        showDetailInfo("man")
        showDetailInfo("women")
    }
}

@Composable
fun showDetailInfo(gender: String){
    if (gender.equals("man")){
        detailResult()
    } else if (gender.equals("women")){
        detailResult()
    }
}

@Composable
fun detailResult(){

}

