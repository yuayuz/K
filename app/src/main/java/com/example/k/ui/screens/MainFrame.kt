package com.example.k.ui.screens

import android.annotation.SuppressLint
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
import com.example.k.data.ui.NavigationBottomItem
import com.example.k.route.AppRoute
import com.example.k.ui.components.BottomNavBar
import com.example.k.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainFrame() {
    val navController= rememberNavController()

    var title by remember { mutableStateOf("Main") }
    val idNavArg = navArgument("id") { type = NavType.LongType }
    val getIdNavArg = { entry: NavBackStackEntry ->
        entry.arguments!!.getLong("id")
    }

    Scaffold(
        modifier = Modifier,
        topBar={
            TopBar(title)
        },
        drawerContent={

        },
        bottomBar = {
        val dataList= listOf(
            NavigationBottomItem(route = AppRoute.CHAT_LIST, icon = Icons.Filled.Home, selectedIcon = Icons.Filled.Home),
            NavigationBottomItem(route = AppRoute.CONTACT, icon = Icons.Filled.DateRange,selectedIcon = Icons.Filled.Home),
            NavigationBottomItem(route = AppRoute.THIRD, icon = Icons.Filled.Person,selectedIcon = Icons.Filled.Home)
        )
        BottomNavBar(navController, dataList){
            navController.navigate(it)
        }
    }
    ) {contentPadding ->
        NavHost(
            navController = navController, startDestination = AppRoute.CHAT_LIST
        ){
            composable(AppRoute.CHAT_LIST) {
                title = "Main"
                ChatListScreen(
                    contentPadding = contentPadding,
                    navToChat = {id:Long->
                        navController.navigate("${AppRoute.CHAT}/${id}")
                    }
                )
            }

            composable(
                "${AppRoute.CHAT}/{id}",
                arguments = listOf(idNavArg)
            ) { entry ->
                title = "Chat"

                val id = getIdNavArg(entry)
                Chat()
            }
            composable(AppRoute.CONTACT) {
                title = "Contact"
                ContactScreen(
                    contentPadding=contentPadding,
                    navToChat = {id:Long->
                        navController.navigate("${AppRoute.CHAT}/${id}")
                    }
                )
            }

            composable(AppRoute.CHAT) {
                title = "Third"
                Third()
            }
        }

    }
}