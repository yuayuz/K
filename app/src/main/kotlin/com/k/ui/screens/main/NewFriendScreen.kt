package com.k.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k.data.newFriend


@Composable
fun NewFriendScreen(
//    contentPadding: PaddingValues,
    navTOChatList: () -> Unit,
    navTOFriendMessage: () -> Unit,
){

    var id by remember {
        mutableStateOf("")
    }

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
                onClick = { navTOChatList() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }

        Column {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .background(Color.White)
                    .padding(40.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = id,
                    placeholder = {
                        Text("请输入帐号")
                    },
                    onValueChange = { str -> id = str },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = null
                        )
                    })

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        newFriend.uid=id.toLong()
                        navTOFriendMessage()
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe)),
                    contentPadding = PaddingValues(12.dp, 16.dp)
                ) {
                    Text("确定", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}



