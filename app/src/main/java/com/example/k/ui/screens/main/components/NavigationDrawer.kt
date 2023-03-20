package com.example.k.ui.screens.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.k.route.AppRoute

data class NavigationDrawerItem(
    val route: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val text:String
)

val dataDrawerList = listOf(
    NavigationDrawerItem(
        route = AppRoute.CHAT_LIST,
        icon = Icons.Filled.Home,
        selectedIcon = Icons.Filled.Home,
        text = "我的消息"
    ),
    NavigationDrawerItem(
        route = AppRoute.REVISE,
        icon = Icons.Filled.DateRange,
        selectedIcon = Icons.Filled.DateRange,
        text = "更改密码"
    ),
/*    NavigationDrawerItem(
        route = AppRoute.THIRD,
        icon = Icons.Filled.Person,
        selectedIcon = Icons.Filled.Person,
        text = "设置"
    )*/
)