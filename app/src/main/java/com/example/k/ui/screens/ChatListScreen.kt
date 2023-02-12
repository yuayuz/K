package com.example.k.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k.ui.components.CardList
import com.example.k.ui.components.ChatItem
import com.example.k.data.ui.listdata

@Composable
fun ChatListScreen(
    contentPadding: PaddingValues,
    navToChat: (Long) -> Unit
){
    Column(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(horizontal = 10.dp)
    ) {
        CardList(
            itemFetcher = { ChatDataList},
            itemRender = {
                ChatItem(navToChat, it)
            }
        )
    }
}

val ChatDataList = List(8) {
    listdata(
        id = 1234567,
        name = "abc",
        body = "人之初，性本善。性相近，习相远"
    )
}
