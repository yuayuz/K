package com.example.k.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.k.data.db.Conversation
import com.example.k.data.viewmodel.ChatListScreenViewModelSingleton
import com.example.k.ui.components.CardList
import com.example.k.ui.components.ChatItem
import java.util.*

@Composable
fun ChatListScreen(
    contentPadding: PaddingValues,
    navToChat: (Long) -> Unit
) {
    val viewModel = ChatListScreenViewModelSingleton()
    val uiState by viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(horizontal = 10.dp)
    ) {
        CardList(
            itemFetcher = {
                uiState.list
            },
            itemRender = {
                ChatItem(navToChat, it)
            }
        )
    }
}

val ChatDataList = List(8) {
    Conversation(
        uid = 123456,
        chat_name = "asd",
        last_msg = "你好！android.",
        ChatId = 123456,
        from = 123456,
        to = 0,
        last_msg_id = 0,
        last_user_name = "a",
        last_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
        chat_type = 1,
        msg_type = 1,
        unread_count = 1,
    )
}
