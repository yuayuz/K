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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
<<<<<<< HEAD
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

=======
fun MainFrame() {

    val ctx = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Main") }
>>>>>>> parent of 512caaf (chat)
    val idNavArg = navArgument("id") { type = NavType.LongType }
    val getIdNavArg = { entry: NavBackStackEntry ->
        entry.arguments!!.getLong("id")
    }
<<<<<<< HEAD

=======
//    var isLogin by remember { mutableStateOf(true) }
    var mainStatus by remember { mutableStateOf(0) }
>>>>>>> parent of 512caaf (chat)
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
<<<<<<< HEAD
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

=======
            composable(AppRoute.CHAT_LIST) {
                mainStatus = 1
                ChatListScreen(
                    contentPadding = MainPadding,
                    navToChat = { id: Long ->
                        navController.navigate("${AppRoute.CHAT}/${id}")
>>>>>>> parent of 512caaf (chat)
                    }
                )
            }

<<<<<<< HEAD
=======
            composable(
                "${AppRoute.CHAT}/{id}",
                arguments = listOf(idNavArg)
            ) { entry ->
                mainStatus = 2
                val id = getIdNavArg(entry)
                Chat()
            }
>>>>>>> parent of 512caaf (chat)
            composable(AppRoute.CONTACT) {
                ContactScreen(
                    navToChat = { id: Long ->
<<<<<<< HEAD
                        navTo("${AppRoute.CHAT}/$id")
                    },
                    showMistake = {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(ctx, "更新错误", Toast.LENGTH_SHORT).show()
                        }
=======
                        navController.navigate("${AppRoute.CHAT}/${id}")
>>>>>>> parent of 512caaf (chat)
                    }
                )
            }

            composable(AppRoute.THIRD) {
<<<<<<< HEAD
                Third(
=======
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
>>>>>>> parent of 512caaf (chat)
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
//                    contentPadding = MainPadding,
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
                reviseScreen(
//                    contentPadding = MainPadding,
                    sNavToLogin = {
                        Toast.makeText(ctx, "帐号密码修改成功", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppRoute.LOGIN)
                    },
                    navToLogin = { navController.navigate(AppRoute.LOGIN) },
                    navToChatList = { navController.navigate(AppRoute.CHAT_LIST) },
                    from = false
                )
            }

            composable(AppRoute.NEW_FRIEND) {
<<<<<<< HEAD
                NewFriendScreen(
//                    contentPadding = MainPadding,
                    navTOChatList = { navTo(AppRoute.CHAT_LIST) },
                    navTOFriendMessage = { navTo(AppRoute.NEW_FRIEND_Message) }
=======
                mainStatus = 0
                newFriendScreen(
//                    contentPadding = MainPadding,
                    navTOChatList = { navController.navigate(AppRoute.CHAT_LIST) },
                    navTOFriendMessage = {}
>>>>>>> parent of 512caaf (chat)
                )
            }

            composable(AppRoute.NEW_FRIEND_Message) {
                FriendMessageScreen(
                    navTONewFriend = { navController.navigate(AppRoute.NEW_FRIEND) },
                    navToChatList = { navController.navigate(AppRoute.CHAT_LIST) }
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

