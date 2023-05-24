package com.k.ui.screens.chat


import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
<<<<<<< HEAD
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.k.data.chat
import com.k.data.db.Message
import com.k.data.db.MessageDbSingleton
import com.k.data.viewmodel.MessageScreenViewModelSingleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "StateFlowValueCalledInComposition"
)
@Composable
fun Chat(
    navController: NavController,
    id: Long
) {
    chat.uid = id
    val ctx = LocalContext.current
    val d: List<Message>
    runBlocking {
        d = withContext(Dispatchers.IO) {
            val s = MessageDbSingleton(ctx)
            return@withContext s.messageDao().getAllByUid(id)!!
        }
    }


    val l = MessageScreenViewModelSingleton
    l().reset(d)
    val uiState by l().state.collectAsState()

=======
import com.k.data.db.Message
import com.k.data.viewmodel.MessageViewModel
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Chat() {
    val viewModel = MessageViewModel()
    val uiState by viewModel.state.collectAsState()
>>>>>>> parent of 512caaf (chat)
    ShowMessage(
        msgList = uiState.list
    )


}
<<<<<<< HEAD

=======
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

/*@Preview
@Composable
fun PreviewChat() {
    Chat()
}*/
>>>>>>> parent of 512caaf (chat)
