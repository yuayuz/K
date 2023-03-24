package com.k.ui.screens.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun BottomNavBar(
    navController: NavController,
    dataList: List<NavigationBottomItem>,
    navTo: (String) -> Unit
) {
    val st by navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
    ) {
        dataList.forEach {
            NavigationBarItem(
                selected = false,
                onClick = { navTo(it.route) },
                icon = {
                    val selected =
                        st?.destination?.hierarchy?.any { x -> it.route == x.route } == true
                    BadgedBox(
                        badge = { Badge { Text("2") } }
                    ) {
                        Icon(
                            imageVector =
                            if (selected)
                                it.selectedIcon
                            else
                                it.icon,
                            contentDescription = it.route
                        )
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun BottomNavBarPreview() {
    val dataList = listOf(
        NavigationBottomItem(
            "_",
            Icons.Filled.Home,
            Icons.Filled.Home,
        ),
        NavigationBottomItem(
            "_",
            Icons.Filled.DateRange,
            Icons.Filled.DateRange,
        ),
        NavigationBottomItem(
            "_",
            Icons.Filled.Person,
            Icons.Filled.Person,
        )
    )
    val rnc = rememberNavController()
    BottomNavBar(rnc, dataList){}
}

