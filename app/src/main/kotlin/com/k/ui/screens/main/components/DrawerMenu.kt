package com.k.ui.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun drawerMenu(
    dataDrawerList: List<NavigationDrawerItem>,
    navTo:(String)->Unit
){

    Column(modifier= Modifier
        .fillMaxWidth()
    ){
        //对每个界面进行遍历访问
        dataDrawerList.forEach{
            Row(
                content = {
                    Text(text = it.text,
                        fontSize=20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {//处理点击动作
                        navTo(it.route)
                    }
            )//end Row
        }
    }
}