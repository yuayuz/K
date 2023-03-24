package com.k.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun newFriendScreen(
    contentPadding: PaddingValues,
    navTOChatList:()->Unit
){
    s()
    Box(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ){
        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = { navTOChatList() },
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
        }
    }
}