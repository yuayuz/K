package com.k.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k.data.db.Account
import com.k.data.viewmodel.CommentListScreenViewModelSingleton


@Composable
fun reviseScreen(
    contentPadding: PaddingValues,
    sNavToLogin: () -> Unit,
    navToLogin:()->Unit,
    navToChatList:()->Unit,
    from:Boolean
) {
    var rt by remember { mutableStateOf(0) }
    var id by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    val ctx = LocalContext.current
    val pwdVisualTransformation = PasswordVisualTransformation()
    var showPwd by remember {
        mutableStateOf(true)
    }

    val transformation = if (showPwd) pwdVisualTransformation else VisualTransformation.None

    Box(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        //上部图片
        /*Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null
        )*/
        Row(
            Modifier.fillMaxWidth(),
            /*horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom,*/
        ) {
            IconButton(
                modifier = Modifier
                    .padding(20.dp),
                onClick = { navToChatList() },
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
                Column {
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
                    TextField(
                        value = pwd, onValueChange = { str -> pwd = str },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("请输入密码")
                        },
                        visualTransformation = transformation,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null
                            )
                        }, trailingIcon = {
                            if (showPwd) {
                                IconButton(onClick = { showPwd = !showPwd }) {
                                    Icon(
                                        imageVector = Icons.Default.Visibility,
                                        contentDescription = null,
                                        Modifier.size(30.dp)
                                    )
                                }
                            } else {
                                IconButton(onClick = { showPwd = !showPwd }) {
                                    Icon(
                                        imageVector = Icons.Default.VisibilityOff,
                                        contentDescription = null,
                                        Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        val l = CommentListScreenViewModelSingleton(
                            ctx = ctx,
                            account = Account(
                                id = id.toLong(),
                                password = pwd
                            )
                        )
                        l.revisePassword()
                        when(from){
                            true->navToLogin()
                            false->sNavToLogin()
                        }
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe)),
                    contentPadding = PaddingValues(12.dp, 16.dp)
                ) {
                    Text("修改密码", color = Color.White, fontSize = 18.sp)
                }

            }
        }
    }
}