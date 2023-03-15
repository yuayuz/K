package com.example.k.ui.screens.chat

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChatPageTopBar(title: String,BU:()->Unit) {
    TopAppBar(
        title = { Text(text = title)},
        navigationIcon = {
            IconButton(
                onClick = {
                    BU()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewChatPageTopBar() {
    ChatPageTopBar("Title",{})
}