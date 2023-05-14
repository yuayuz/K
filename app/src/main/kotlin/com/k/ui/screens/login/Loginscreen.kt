package com.k.ui.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.k.data.repositories.AccountRepositories
import com.k.data.user
import com.k.route.AppRoute
import kotlinx.coroutines.*


@SuppressLint("SuspiciousIndentation")
@Composable
fun loginScreen(
    onLoginSuccess: () -> Unit,
//    navToMain: () -> Unit,
//    navToLogin: () -> Unit,
//    showNoRegister: () -> Unit,
//    showMistake: () -> Unit,
//    navToRevise: () -> Unit
) {
    val navController = rememberNavController()
    var id by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    val ctx = LocalContext.current
    val pwdVisualTransformation = PasswordVisualTransformation()
    var showPwd by remember {
        mutableStateOf(true)
    }

    val transformation = if (showPwd) pwdVisualTransformation else VisualTransformation.None

    Box(
        Modifier.fillMaxSize()
    ) {
        //上部图片
        /*Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null
        )*/
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom,
        ) {
            TextButton(
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    navController.navigate(AppRoute.REGISTER)
                },
                /*colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe))*/
            ) {
                Text(
                    text = "注册",
                    fontSize = 16.sp,
                    color = Color.Gray
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
                        //把查询功能提出来
                        /*val l = CommentListScreenViewModelSingleton(
                            ctx = ctx,
                            id = id.toLong(),
                            name = null,
                            password = pwd
                        )*/
                        val accountRepositories= AccountRepositories(ctx)
                        val data=accountRepositories.accountQueryOne(id.toLong(),pwd)
                        user.id = id.toLong()
                        user.name= data?.name.toString()
                        user.password = pwd
                        val j= when {
                            //无帐号信息
                            data == null -> 0
                            //密码正确
                            pwd == data.password -> 1
                            //密码错误
                            else -> 2
                        }
                        when (j) {
                            0 -> Toast.makeText(ctx, "帐号未注册", Toast.LENGTH_SHORT).show()
                            1 -> onLoginSuccess()
                            2 -> Toast.makeText(ctx, "帐号/密码错误", Toast.LENGTH_SHORT).show()
                        }
                        /*val status: Int
                        runBlocking {
                            withContext(Dispatchers.IO) {
                                status = l.queryOne()
                            }
                        }
                        user.id = id.toLong()
                        user.password = pwd
                        when (status) {
                            1 -> onLoginSuccess()
                            0 -> Toast.makeText(ctx, "帐号未注册", Toast.LENGTH_SHORT).show()
                            2 -> Toast.makeText(ctx, "帐号/密码错误", Toast.LENGTH_SHORT).show()
                        }*/

                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe)),
                    contentPadding = PaddingValues(12.dp, 16.dp)
                ) {
                    Text("登录", color = Color.White, fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(100.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(
                        modifier = Modifier
                            .padding(20.dp),
                        onClick = {
                            navController.navigate(AppRoute.REVISE)
                        },
                        /*colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe))*/
                    ) {
                        Text(
                            text = "忘记密码？",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
    NavHost(navController = navController, startDestination = AppRoute.LOGIN) {

        composable(AppRoute.LOGIN) {

        }

        composable(AppRoute.REGISTER) {
            registerScreen(
                sNavToLogin = {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(ctx, "帐号注册成功", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppRoute.LOGIN)
                    }
                },
                navToLogin = {
                    CoroutineScope(Dispatchers.Main).launch {
                        navController.navigate(AppRoute.LOGIN)
                    }
                },
                showRegistered = {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(ctx, "帐号已注册", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppRoute.LOGIN)
                    }
                }
            )
        }
        composable(AppRoute.REVISE) {
            reviseScreen(
                sNavToLogin = {
                    Toast.makeText(ctx, "帐号密码修改成功", Toast.LENGTH_SHORT).show()
                    navController.navigate(AppRoute.LOGIN)
                },
                navToLogin = { navController.navigate(AppRoute.LOGIN) },
                navToChatList = {},
                from = true
            )
        }
    }
}

/*@Preview
@Composable
fun previewLoginScreen() {
    loginScreen({},{}, {},{},{})
}*/
