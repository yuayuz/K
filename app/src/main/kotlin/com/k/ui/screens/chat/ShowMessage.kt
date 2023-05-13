package com.k.ui.screens.chat

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.k.data.db.Message
import com.k.ui.screens.chat.components.MessageLeftItem
import com.k.ui.screens.chat.components.MessageRightItem

@Composable
fun ShowMessage(
    msgList:List<Message>
){
    LazyColumn {
        items(items = msgList){
                item ->
            if (!item.is_me)
                MessageLeftItem(msg = item)
            else
                MessageRightItem(msg = item)
        }
    }
}

