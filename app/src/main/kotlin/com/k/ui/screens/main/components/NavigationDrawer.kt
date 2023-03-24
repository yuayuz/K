package com.k.ui.screens.main.components

import com.k.route.AppRoute

data class NavigationDrawerItem(
    val route: String,
    val text:String
)

val dataDrawerList = listOf(
    NavigationDrawerItem(
        route = AppRoute.CHAT_LIST,
        text = "我的消息"
    ),
    NavigationDrawerItem(
        route = AppRoute.REVISE,
        text = "更改密码"
    ),
    NavigationDrawerItem(
        route = AppRoute.NEW_FRIEND,
        text = "新朋友"
    )
)