package com.example.k.ui.screens.main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.k.route.AppRoute
import com.example.k.ui.screens.chat.Chat
import com.example.k.ui.screens.chat.components.ChatPageBottomBar
import com.example.k.ui.screens.chat.components.ChatPageTopBar
import com.example.k.ui.screens.login.loginScreen
import com.example.k.ui.screens.login.registerScreen
import com.example.k.ui.screens.login.reviseScreen
import com.example.k.ui.screens.main.components.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainFrame() {

    val ctx = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Main") }
    val idNavArg = navArgument("id") { type = NavType.LongType }
    val getIdNavArg = { entry: NavBackStackEntry ->
        entry.arguments!!.getLong("id")
    }
//    var isLogin by remember { mutableStateOf(true) }
    var mainStatus by remember { mutableStateOf(0) }
    Scaffold(
//        contentWindowInsets = WindowInsetsEmpty,
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (mainStatus == 1) {
                TopBar(title) {
                    scope.launch {
                        if (drawerState.isClosed) drawerState.open() else drawerState.close()
                    }
                }
            } else if (mainStatus == 2) {
                ChatPageTopBar(title = "1") {
                    navController.navigate(AppRoute.CHAT_LIST)
                }
            }
        },
        drawerContent = {
            DrawerContentTopBar()
            drawerMenu(
                dataDrawerList
            ) {
                navController.navigate(it)
            }
        },
        bottomBar = {
            dataList
            if (mainStatus == 1) {
                BottomNavBar(navController, dataList) {
                    navController.navigate(it)
                }
            } else if (mainStatus == 2) {
                ChatPageBottomBar()
            }
        }
    ) { MainPadding ->
        NavHost(
            navController = navController, startDestination = AppRoute.LOGIN//AppRoute.CHAT_LIST
        ) {
            composable(AppRoute.CHAT_LIST) {
                mainStatus = 1
                ChatListScreen(
                    contentPadding = MainPadding,
                    navToChat = { id: Long ->
                        navController.navigate("${AppRoute.CHAT}/${id}")
                    }
                )
            }

            composable(
                "${AppRoute.CHAT}/{id}",
                arguments = listOf(idNavArg)
            ) { entry ->
                mainStatus = 2
                val id = getIdNavArg(entry)
                Chat()
            }
            composable(AppRoute.CONTACT) {
                mainStatus = 1
                ContactScreen(
                    contentPadding = MainPadding,
                    navToChat = { id: Long ->
                        navController.navigate("${AppRoute.CHAT}/${id}")
                    }
                )
            }

            composable(AppRoute.THIRD) {
                mainStatus = 1
                Third(MainPadding)
            }
            composable(AppRoute.LOGIN) {
                mainStatus = 0
                loginScreen(
                    contentPadding = MainPadding,
                    navToMain = {
                        CoroutineScope(Dispatchers.Main).launch {
                            navController.navigate(AppRoute.CHAT_LIST)
                        }
                    },
                    navToLogin = { navController.navigate(AppRoute.REGISTER) },
                    showNoRegister = {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(ctx, "帐号未注册", Toast.LENGTH_SHORT).show()
                            navController.navigate(AppRoute.REGISTER)
                        }
                    },
                    showMistake = {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(ctx, "帐号/密码错误", Toast.LENGTH_SHORT).show()
                        }
                    },
                    navToRevise = {
                        navController.navigate(AppRoute.REVISE)
                    }
                )
            }

            composable(AppRoute.MAIN) {
                MainFrame()
            }

            composable(AppRoute.REGISTER) {
                mainStatus = 0
                registerScreen(
                    contentPadding = MainPadding,
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
                mainStatus = 0
                reviseScreen(
                    contentPadding = MainPadding,
                    sNavToLogin = {
                        Toast.makeText(ctx, "帐号密码修改成功", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppRoute.LOGIN)
                    },
                    navToLogin = { navController.navigate(AppRoute.LOGIN) },
                    navToChatList = {},
                    from = true
                )
            }

            composable(AppRoute.FORGET) {
                mainStatus = 0
                reviseScreen(
                    contentPadding = MainPadding,
                    sNavToLogin = {
                        Toast.makeText(ctx, "帐号密码修改成功", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppRoute.LOGIN)
                    },
                    navToLogin = { navController.navigate(AppRoute.LOGIN) },
                    navToChatList = { navController.navigate(AppRoute.CHAT_LIST) },
                    from = false
                )
            }
        }

    }
}

