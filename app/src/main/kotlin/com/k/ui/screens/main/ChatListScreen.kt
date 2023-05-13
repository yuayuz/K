package com.k.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.k.data.db.Conversation
import com.k.data.db.ConversationDbSingleton
import com.k.data.user
import com.k.data.viewmodel.ChatListScreenViewModelSingleton
import com.k.ui.components.CardList
import com.k.ui.components.ChatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Composable
fun ChatListScreen(
    contentPadding: PaddingValues,
    navToChat: (Long) -> Unit
) {
    val ctx = LocalContext.current
    val d :List<Conversation>

    runBlocking{
        d = withContext(Dispatchers.IO){
            val s = ConversationDbSingleton(ctx)
            return@withContext s.conversationDao().getOneByChatId(user.id)!!
        }
    }

    val viewModel = ChatListScreenViewModelSingleton()
    viewModel.reset(d)
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

//val ChatDataList = List(8) {
//    Conversation(
//        uid = 123456,
//        chat_name = "asd",
//        last_msg = "你好！android.",
//        ChatId = 123456,
//        from = 123456,
//        to = 0,
//        last_msg_id = 0,
//        last_user_name = "a",
//        last_time = LocalDateTime.now(),
//        chat_type = 1,
//        msg_type = 1,
//        unread_count = 1,
//    )
//}
