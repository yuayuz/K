package com.k.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FriendMessageScreen(
    navTONewFriend:()->Unit,
    navToChatList: () -> Unit
){
    Box(
        Modifier.fillMaxSize()
//            .padding(contentPadding)
    ) {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom,
        ) {
            IconButton(
                modifier = Modifier
                    .padding(20.dp),
                onClick = { navTONewFriend() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }

        Column() {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .background(Color.White)
                    .padding(40.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "姓名")
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "id")
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "生日日期")

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        navToChatList()
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe)),
                    contentPadding = PaddingValues(12.dp, 16.dp)
                ) {
                    androidx.compose.material.Text("确定", color = Color.White, fontSize = 18.sp)
                }
            }
        }

    }
}

@Composable
@Preview
fun PreviewFriendMessage(){
    FriendMessageScreen ({},{})
}