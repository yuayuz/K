package com.k.ui.screens.main.components

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopBar(title: String,DO:()->Unit) {
    TopAppBar(
        title = { Text(text = title)},
        navigationIcon = {
            IconButton(
                onClick = {
                    DO()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun TopBar() {
    TopBar("Title") {}
}