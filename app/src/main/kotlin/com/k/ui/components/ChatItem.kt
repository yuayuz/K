package com.k.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k.data.db.Conversation
import java.time.LocalDateTime
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun ChatItem(
    navToChat: (Long) -> Unit,
    data: Conversation
) {

    Card(
        modifier = Modifier
            .clickable { navToChat(data.uid) },
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {

                    Image(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.Default.Sms,
                        contentDescription = "chat"
                    )

                    Column {
                        Text(text = data.chat_name)
                        Text(
                            text = data.last_msg,
                            modifier = Modifier,
                            maxLines = 1,
                            fontSize = 20.sp
                        )
                    }

                }
            }

        }


    }

}


@Preview
@Composable
fun PreviewChatItem() {
    val data = Conversation(
        uid = 123456,
        chat_name = "asd",
        last_msg = "你好！android.",
        ChatId = 123456,
        from = 123456,
        to = 0,
        last_msg_id = 0,
        last_user_name = "a",
        last_time = LocalDateTime.now(),
        chat_type = 1,
        msg_type = 1,
        unread_count = 1,
    )
    ChatItem(
        navToChat = {},
        data = data
    )
}