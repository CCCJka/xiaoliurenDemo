package com.cccjka.liuren.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        liurenResult(data.TianGanDiZhiYear, data.TianGanDiZhiMonth, data.TianGanDiZhiDay)
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
            fontSize = 70.sp,
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        showSexagenaryCycle(data.TianGanDiZhiYear, data.TianGanDiZhiMonth, data.TianGanDiZhiDay)
        showCurrentYear(data.LYear)
        xiangChongSuiSha(data)
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
fun  liurenResult(year: String, month: String, day: String){
    val context = LocalContext.current
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra("year", year)
    intent.putExtra("month", month)
    intent.putExtra("day", day)
    Text(text = CommonUtils.getResult(0), modifier = Modifier
        .clickable { CommonUtils.navigatorActivity(context, intent) }
        .fillMaxSize(),
        textAlign = TextAlign.Center)
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
fun DetailView(list: List<String>){
    val backgroundPic = painterResource(R.drawable.paper)
    var status by remember { mutableStateOf(0) }
    Image(painter = backgroundPic, contentDescription = null,
        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillHeight)
    Column(modifier = Modifier.fillMaxSize()){
        Row (modifier = Modifier.fillMaxWidth()){
            Text(text = "男", fontSize = 30.sp,
                modifier = Modifier
                    .weight(1f)
                    .clickable { status = 2 },
                textAlign = TextAlign.Center)
            Text(text = "女", fontSize = 30.sp,
                modifier = Modifier
                    .weight(1f)
                    .clickable { status = 1 },
                textAlign = TextAlign.Center)
            Text(text = "通用", fontSize = 30.sp,
                modifier = Modifier
                    .weight(1f)
                    .clickable { status = 0 },
                textAlign = TextAlign.Center)
        }
        showDetailResult(status, list)

    }
}

@Composable
fun showDetailResult(startAs: Int, list: List<String>){
    val context = LocalContext.current
    val list = CommonUtils.returnClass(context, list)
    Column {
        Text(text = CommonUtils.getResult(startAs),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth())
        showInfo(list[0])
        showInfo(list[1])
        showInfo(list[2])
        showInfo(list[3])
    }
}

@Composable
fun showInfo(detail: String){
    Text(text = detail,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center)
}

/*********   DetailActivity End   ***********/

