package com.k.ui.screens.chat

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.k.data.db.Message
import com.k.ui.screens.chat.components.MessageLeftItem
import com.k.ui.screens.chat.components.MessageRightItem
import java.util.*

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

@Preview
@Composable
fun PreviewShowMessage(){
    val msg:List<Message> = listOf(Message(
        msg_id = 1,
        uid = 123,
        is_me = false,
        from = 195,
        from_name = "小明",
        to = 187,
        to_name = "小红",
        chat_type = 1,
        msg_type = 1,
        msg = "人之初，性本善。性相近，习相远。\n" +
                "\n" +
                "苟不教，性乃迁。教之道，贵以专。\n" +
                "\n" +
                "昔孟母，择邻处。子不学，断机杼。\n" +
                "\n" +
                "窦燕山，有义方。教五子，名俱扬。",
        send_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
        send_status = 1),
        Message(
            msg_id = 1,
            uid = 123,
            is_me = true,
            from = 195,
            from_name = "小明",
            to = 187,
            to_name = "小红",
            chat_type = 1,
            msg_type = 1,
            msg = "人之初，性本善。性相近，习相远。\n" +
                    "\n" +
                    "苟不教，性乃迁。教之道，贵以专。\n" +
                    "\n" +
                    "昔孟母，择邻处。子不学，断机杼。\n" +
                    "\n" +
                    "窦燕山，有义方。教五子，名俱扬。",
            send_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
            send_status = 1
        ),
        Message(
            msg_id = 1,
            uid = 123,
            is_me = false,
            from = 195,
            from_name = "小明",
            to = 187,
            to_name = "小红",
            chat_type = 1,
            msg_type = 1,
            msg = "人之初，性本善。性相近，习相远。\n" +
                    "\n" +
                    "苟不教，性乃迁。教之道，贵以专。\n" +
                    "\n" +
                    "昔孟母，择邻处。子不学，断机杼。\n" +
                    "\n" +
                    "窦燕山，有义方。教五子，名俱扬。",
            send_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
            send_status = 1),
        Message(
            msg_id = 1,
            uid = 123,
            is_me = true,
            from = 195,
            from_name = "小明",
            to = 187,
            to_name = "小红",
            chat_type = 1,
            msg_type = 1,
            msg = "人之初，性本善。性相近，习相远。\n" +
                    "\n" +
                    "苟不教，性乃迁。教之道，贵以专。\n" +
                    "\n" +
                    "昔孟母，择邻处。子不学，断机杼。\n" +
                    "\n" +
                    "窦燕山，有义方。教五子，名俱扬。",
            send_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
            send_status = 1
        ),
        Message(
            msg_id = 1,
            uid = 123,
            is_me = false,
            from = 195,
            from_name = "小明",
            to = 187,
            to_name = "小红",
            chat_type = 1,
            msg_type = 1,
            msg = "人之初，性本善。性相近，习相远。\n" +
                    "\n" +
                    "苟不教，性乃迁。教之道，贵以专。\n" +
                    "\n" +
                    "昔孟母，择邻处。子不学，断机杼。\n" +
                    "\n" +
                    "窦燕山，有义方。教五子，名俱扬。",
            send_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
            send_status = 1),
        Message(
            msg_id = 1,
            uid = 123,
            is_me = true,
            from = 195,
            from_name = "小明",
            to = 187,
            to_name = "小红",
            chat_type = 1,
            msg_type = 1,
            msg = "人之初，性本善。性相近，习相远。\n" +
                    "\n" +
                    "苟不教，性乃迁。教之道，贵以专。\n" +
                    "\n" +
                    "昔孟母，择邻处。子不学，断机杼。\n" +
                    "\n" +
                    "窦燕山，有义方。教五子，名俱扬。",
            send_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
            send_status = 1
        ),
        Message(
            msg_id = 1,
            uid = 123,
            is_me = false,
            from = 195,
            from_name = "小明",
            to = 187,
            to_name = "小红",
            chat_type = 1,
            msg_type = 1,
            msg = "人之初，性本善。性相近，习相远。\n" +
                    "\n" +
                    "苟不教，性乃迁。教之道，贵以专。\n" +
                    "\n" +
                    "昔孟母，择邻处。子不学，断机杼。\n" +
                    "\n" +
                    "窦燕山，有义方。教五子，名俱扬。",
            send_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
            send_status = 1),
        Message(
            msg_id = 1,
            uid = 123,
            is_me = true,
            from = 195,
            from_name = "小明",
            to = 187,
            to_name = "小红",
            chat_type = 1,
            msg_type = 1,
            msg = "人之初，性本善。性相近，习相远。\n" +
                    "\n" +
                    "苟不教，性乃迁。教之道，贵以专。\n" +
                    "\n" +
                    "昔孟母，择邻处。子不学，断机杼。\n" +
                    "\n" +
                    "窦燕山，有义方。教五子，名俱扬。",
            send_time = Date(2015 - 1900, 11, 30, 23, 59, 59),
            send_status = 1
        ),
        )
    ShowMessage(msgList = msg )
}