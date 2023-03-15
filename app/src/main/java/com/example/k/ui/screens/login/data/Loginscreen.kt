package com.example.k.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k.R
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController

@SuppressLint("SuspiciousIndentation")
@Composable
fun Loginscreen(cb: ()->Unit) {
    val snackBarHostState = remember { SnackbarHostState() }
    var name by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }

    val pwdVisualTransformation = PasswordVisualTransformation()
    var showPwd by remember {
        mutableStateOf(true)
    }

    val transformation = if (showPwd) pwdVisualTransformation else VisualTransformation.None

        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.login) ,
                contentDescription = null
            )
            Text(
                text = "注册",
                color = Color.Black ,
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
            Column() {
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .background(Color.White)
                        .padding(40.dp)
                        .fillMaxWidth()
                ) {
                    Column() {
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = name,
                            placeholder = {
                                Text("请输入用户名")
                            },
                            onValueChange = { str -> name = str },
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
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()) {
                        Text(text = "快捷登录", fontSize = 16.sp, color = Color.Gray)
                        Text(text = "忘记密码", fontSize = 16.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    val navController = rememberNavController()
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            if (name == "test" && pwd == "123") {
                                //navController.navigate("MainFrame")
                                 /*Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()*/
                                cb()//go to chat list
                            } else {
                                /*Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show()*/
                            }
                        },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe)),
                        contentPadding = PaddingValues(12.dp, 16.dp)
                    ) {
                        Text("登录", color = Color.White, fontSize = 18.sp)
                    }

//                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
}

@Preview
@Composable
fun PreviewLoginscreen(){
    Loginscreen({})
}
