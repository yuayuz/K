package com.k.ui.screens.main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.k.data.chat
import com.k.route.AppRoute
import com.k.ui.screens.chat.Chat
import com.k.ui.screens.chat.components.ChatPageBottomBar
import com.k.ui.screens.chat.components.ChatPageTopBar
import com.k.ui.screens.login.loginScreen
import com.k.ui.screens.login.registerScreen
import com.k.ui.screens.login.reviseScreen
import com.k.ui.screens.main.components.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainFrame() {



    var id by remember {
        mutableStateOf(Long.MAX_VALUE)
    }
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

    fun navTo(dest: String) {
        navController.navigate(dest) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }

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
            composable(
                AppRoute.CHAT_LIST
            ) {
                mainStatus = 1
                ChatListScreen(
                    contentPadding = MainPadding,
                    navToChat = { id: Long ->
                        navTo("${AppRoute.CHAT}/${id}")
                    }
                )
            }

            composable(
                "${AppRoute.CHAT}/{id}",
                arguments = listOf(idNavArg)
            ) { entry ->
                mainStatus = 2
                val id = getIdNavArg(entry)
                chat.uid=id
                Chat(id)
            }
            composable(AppRoute.CONTACT) {
                mainStatus = 1
                ContactScreen(
                    contentPadding = MainPadding,
                    navToChat = { id: Long ->
                        navTo("${AppRoute.CHAT}/${id}")
                    },
                    showMistake = {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(ctx, "更新错误", Toast.LENGTH_SHORT).show()
                        }
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
                            navTo(AppRoute.CHAT_LIST)
                        }
                    },
                    navToLogin = { navTo(AppRoute.REGISTER) },
                    showNoRegister = {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(ctx, "帐号未注册", Toast.LENGTH_SHORT).show()
                            navTo(AppRoute.REGISTER)
                        }
                    },
                    showMistake = {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(ctx, "帐号/密码错误", Toast.LENGTH_SHORT).show()
                        }
                    },
                    navToRevise = {
                        navTo(AppRoute.REVISE)
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
                            navTo(AppRoute.LOGIN)
                        }
                    },
                    navToLogin = {
                        CoroutineScope(Dispatchers.Main).launch {
                            navTo(AppRoute.LOGIN)
                        }
                    },
                    showRegistered = {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(ctx, "帐号已注册", Toast.LENGTH_SHORT).show()
                            navTo(AppRoute.LOGIN)
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
                        navTo(AppRoute.LOGIN)
                    },
                    navToLogin = { navTo(AppRoute.LOGIN) },
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
                        navTo(AppRoute.LOGIN)
                    },
                    navToLogin = { navTo(AppRoute.LOGIN) },
                    navToChatList = { navTo(AppRoute.CHAT_LIST) },
                    from = false
                )
            }

            composable(AppRoute.NEW_FRIEND) {
                mainStatus = 0
                NewFriendScreen(
//                    contentPadding = MainPadding,
                    navTOChatList = { navTo(AppRoute.CHAT_LIST) },
                    navTOFriendMessage = {navTo(AppRoute.NEW_FRIEND_Message)}
                )
            }

            composable(AppRoute.NEW_FRIEND_Message) {
                mainStatus = 0
                FriendMessageScreen(
                    navTONewFriend = { navTo(AppRoute.NEW_FRIEND) },
                    navToChatList = { navTo(AppRoute.CHAT_LIST) }
                )
            }

        }
    }
}

