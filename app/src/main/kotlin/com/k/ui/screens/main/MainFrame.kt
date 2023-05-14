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
import com.k.ui.screens.login.registerScreen
import com.k.ui.screens.login.reviseScreen
import com.k.ui.screens.main.components.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFrame(
    onLogout: () -> Unit
) {
    val scaffoldState= rememberScaffoldState()
    val coroutineScope= rememberCoroutineScope()
    val navController = rememberNavController()
    val ctx = LocalContext.current

    fun navTo(dest: String) {
        navController.navigate(dest) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }

    val idNavArg = navArgument("id") { type = NavType.LongType }
    val getIdNavArg = { entry: NavBackStackEntry ->
        entry.arguments!!.getLong("id")
    }

    Scaffold(
        scaffoldState=scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = "Main"
            ) { coroutineScope.launch { scaffoldState.drawerState.open() } }
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
            BottomNavBar(navController, dataList) {
                navController.navigate(it)
            }
        },

        ) {
        NavHost(navController = navController, startDestination = AppRoute.LOGIN) {

            composable(AppRoute.APP){

            }

            composable(
                AppRoute.CHAT_LIST
            ) {
                ChatListScreen(
                    navToChat = {
//                            id:Long ->
//                        navTo("${AppRoute.CHAT}/$id")
                        navController.navigate(AppRoute.THIRD)

                    }
                )
            }

            composable(AppRoute.CONTACT) {
                ContactScreen(
                    navToChat = { id: Long ->
                        navTo("${AppRoute.CHAT}/$id")
                    },
                    showMistake = {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(ctx, "更新错误", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            composable(AppRoute.THIRD) {
                Third(
                )
            }

            composable(
                "${AppRoute.CHAT}/{id}",
                arguments = listOf(idNavArg)
            ) { entry ->
                val id = getIdNavArg(entry)
                chat.uid = id
                Chat(navController, id)
            }


            composable(AppRoute.REGISTER) {
                registerScreen(
//                    contentPadding = MainPadding,
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
                reviseScreen(
//                    contentPadding = MainPadding,
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
                reviseScreen(
//                    contentPadding = MainPadding,
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
                NewFriendScreen(
//                    contentPadding = MainPadding,
                    navTOChatList = { navTo(AppRoute.CHAT_LIST) },
                    navTOFriendMessage = { navTo(AppRoute.NEW_FRIEND_Message) }
                )
            }

            composable(AppRoute.NEW_FRIEND_Message) {
                FriendMessageScreen(
                    navTONewFriend = { navTo(AppRoute.NEW_FRIEND) },
                    navToChatList = { navTo(AppRoute.CHAT_LIST) }
                )
            }
            /*composable(AppRoute.LOGIN) {
                loginScreen(
                   *//* navToMain = {
                        CoroutineScope(Dispatchers.Main).launch {
                            navTo(AppRoute.MAIN)
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
                    }*//*
                )
            }*/


        }
    }

}

