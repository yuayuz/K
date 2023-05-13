package com.k.ui.screens.chat.components

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.k.data.chat

@Composable
fun ChatPageTopBar(title: String,BU:()->Unit) {
    TopAppBar(
        title = { Text(text = chat.name)},
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
    ChatPageTopBar("Title") {}
}