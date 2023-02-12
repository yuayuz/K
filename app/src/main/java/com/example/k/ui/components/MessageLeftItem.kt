package com.example.k.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k.data.ui.listdata

@Composable
fun MessageLeftItem(msg : listdata){
    Row(
        modifier = Modifier.padding(all = 10.dp)
                            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {
        //图像
        /*
        Image(
            painter = painterResource(id = msg.img),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)//图像大小
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.secondary, CircleShape)

        )
        */
        //左右间隔
        Spacer(modifier = Modifier.width(10.dp))

        //重组：可组合函数可以使用 remember 将本地状态存储在内存中，并跟踪传递给 mutableStateOf 的值的变化。
        // 该值更新时，系统会自动重新绘制使用此状态的可组合项（及其子项）
        //通过使用 Compose 的状态 API（如 remember 和 mutableStateOf），系统会在状态发生任何变化时自动更新界面。
        /*
        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded)
                MaterialTheme.colorScheme.surface
            else
                MaterialTheme.colorScheme.surface
        )
        */
        Column() {
            Text(
                text = msg.name,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall
            )

            //上下间隔
            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                shape = RectangleShape,
                shadowElevation = 1.dp,
                tonalElevation = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier
                        //.clickable { isExpanded = !isExpanded }
                        .padding(all = 4.dp),
                    //maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewMessageLeftItem(){
    MessageLeftItem(msg =
        listdata(
            id = 123,
            name = "小明",
            body = "人之初，性本善。性相近，习相远。\n" +
                    "\n" +
                    "苟不教，性乃迁。教之道，贵以专。\n" +
                    "\n" +
                    "昔孟母，择邻处。子不学，断机杼。\n" +
                    "\n" +
                    "窦燕山，有义方。教五子，名俱扬。"
        )
    )

}
