package com.k.ui.screens.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k.R
import com.k.ui.theme.Purple80

@Composable
fun DrawerContentTopBar() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Purple80)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.login),
                    contentDescription = "logo图标",
                    modifier = Modifier
//                        .width()
                        .height(60.dp)
                        .clip(shape = CircleShape)
                        .background(Color.Black)
                )
            }//end Column
            Column {
                Text(text = "")
                Text(text = "")
            }
        }//end Column
    }
}

@Preview
@Composable
fun PreviewDrawerContentTopBar() {
    DrawerContentTopBar()
}