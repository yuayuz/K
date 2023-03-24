package com.k.ui.screens.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.k.route.AppRoute

data class NavigationBottomItem(
    val route: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
)

val dataList = listOf(
    NavigationBottomItem(
        route = AppRoute.CHAT_LIST,
        icon = Icons.Filled.Home,
        selectedIcon = Icons.Filled.Home
    ),
    NavigationBottomItem(
        route = AppRoute.CONTACT,
        icon = Icons.Filled.DateRange,
        selectedIcon = Icons.Filled.DateRange
    ),
    NavigationBottomItem(
        route = AppRoute.THIRD,
        icon = Icons.Filled.Person,
        selectedIcon = Icons.Filled.Person
    )
)
