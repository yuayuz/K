package com.example.k.ui.screens.chat


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.k.data.db.Message
import com.example.k.route.AppRoute
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Chat(navController:NavController
) {
    val msg: List<Message> = listOf(
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
            send_status = 1
        ),
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
            send_status = 1
        ),
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
            send_status = 1
        ),
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
    val ChatNavController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ChatPageTopBar("小红") {
                navController.navigate(AppRoute.CHAT_LIST)
            }
        },
        bottomBar = { ChatPageBottomBar() }
    ) { ChatPadding ->
        NavHost(navController = ChatNavController , startDestination = AppRoute.CHAT) {
            composable(AppRoute.CHAT) {
                ShowMessage(msglist = msg)
            }
        }
//        ShowMessage(msglist =msg)
    }
}

/*
@Preview
@Composable
fun PriviewChat() {
    Chat()
}*/
