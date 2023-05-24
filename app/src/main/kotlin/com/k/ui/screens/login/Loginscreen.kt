package com.k.ui.screens.login

import android.annotation.SuppressLint
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
import androidx.navigation.NavController
import com.k.data.db.Account
import com.k.data.user
import com.k.data.viewmodel.CommentListScreenViewModelSingleton
import com.k.route.AppRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@SuppressLint("SuspiciousIndentation", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun loginScreen(
//    contentPadding: PaddingValues,
    navController:NavController,
    onLoginSuccess: () -> Unit,
    /*navToMain: () -> Unit,
    navToLogin: () -> Unit,
    showNoRegister:()->Unit,
    showMistake:()->Unit,
    navToRevise:()->Unit*/
) {
    var id by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    val ctx = LocalContext.current
    val pwdVisualTransformation = PasswordVisualTransformation()
    var showPwd by remember {
        mutableStateOf(true)
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val transformation = if (showPwd) pwdVisualTransformation else VisualTransformation.None

    Scaffold() {
        Box(
            Modifier.fillMaxSize()
//            .padding(contentPadding)
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
                    onClick = {  },
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
                            val l = CommentListScreenViewModelSingleton(
                                ctx = ctx,
                                account = Account(
                                    id = id.toLong(),
                                    password = pwd
                                )
                            )

                            val judge :Any
                            runBlocking{
                                judge = withContext(Dispatchers.IO){
                                    var s=2;
                                    when (l.queryOne()) {
                                        1 -> return@withContext 1
//                                    navController.navigate(AppRoute.MAIN)
                                        0 -> return@withContext 0
//                                navController.navigate(AppRoute.REGISTER)
                                        2 -> return@withContext 2
                                        else -> {}
                                    }
                                }
                            }
                            when(judge){
                                1 -> navController.navigate(AppRoute.APP)
                                0 -> navController.navigate(AppRoute.REGISTER)
                                2 -> scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = " 帐号或密码错误！！！"
                                    )
                                }
                            }
                            user.id=id.toLong()
                            user.password= pwd
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
    }
}

/*@Preview
@Composable
fun previewLoginScreen() {
    loginScreen({},{}, {},{},{})
}*/
