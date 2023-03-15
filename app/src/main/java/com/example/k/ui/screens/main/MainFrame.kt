package com.example.k.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.k.ui.screens.main.NavigationBottomItem
import com.example.k.route.AppRoute
import com.example.k.ui.components.BottomNavBar
import com.example.k.ui.components.TopBar
import com.example.k.ui.screens.ChatListScreen
import com.example.k.ui.screens.ContactScreen
import com.example.k.ui.screens.Third
import com.example.k.ui.screens.chat.Chat
import com.example.k.ui.screens.login.Loginscreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainFrame() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val drawerState=scaffoldState.drawerState
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Main") }
    val idNavArg = navArgument("id") { type = NavType.LongType }
    val getIdNavArg = { entry: NavBackStackEntry ->
        entry.arguments!!.getLong("id")
    }
    var isLogin by remember { mutableStateOf(true) }
    var MainStatus by remember { mutableStateOf(true) }
    Scaffold(
//        contentWindowInsets = WindowInsetsEmpty,
        scaffoldState=scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (MainStatus) {
                TopBar(title){
                    scope.launch{
                        if(drawerState.isClosed) drawerState.open() else drawerState.close()
                    }
                }
            }
        },
        drawerContent = {
            Text(text = "drawerContent")
        },
        bottomBar = {
            val dataList = listOf(
                NavigationBottomItem(
                    route = AppRoute.CHAT_LIST,
                    icon = Icons.Filled.Home,
                    selectedIcon = Icons.Filled.Home
                ),
                NavigationBottomItem(
                    route = AppRoute.CONTACT,
                    icon = Icons.Filled.DateRange,
                    selectedIcon = Icons.Filled.Home
                ),
                NavigationBottomItem(
                    route = AppRoute.THIRD,
                    icon = Icons.Filled.Person,
                    selectedIcon = Icons.Filled.Home
                )
            )
            if (MainStatus) {
                BottomNavBar(navController, dataList) {
                    navController.navigate(it)
                }
            }

        }
    ) { MainPadding ->
        NavHost(
            navController = navController, startDestination = AppRoute.LOGIN//AppRoute.CHAT_LIST
        ) {
            composable(AppRoute.CHAT_LIST) {
                MainStatus = true
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
                MainStatus = false
                val id = getIdNavArg(entry)
                Chat(navController)
            }
            composable(AppRoute.CONTACT) {
                MainStatus = true
                ContactScreen(
                    contentPadding = MainPadding,
                    navToChat = { id: Long ->
                        navController.navigate("${AppRoute.CHAT}/${id}")
                    }
                )
            }

            composable(AppRoute.THIRD) {
                title = "Third"
                MainStatus = true
                Third(MainPadding)
            }
            composable(AppRoute.LOGIN) {
                title = "Login"
                MainStatus = false
                Loginscreen {
                    navController.navigate(AppRoute.CHAT_LIST)
                }
            }

            composable(AppRoute.MAIN) {
                MainStatus = true
                MainFrame()
            }


        }

    }
}