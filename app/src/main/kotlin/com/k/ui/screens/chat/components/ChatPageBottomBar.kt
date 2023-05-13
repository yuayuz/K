package com.k.ui.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.k.data.chat
import com.k.data.db.Message
import com.k.data.db.MessageDbSingleton
import com.k.data.user
import com.k.data.viewmodel.MessageScreenViewModelSingleton
import com.k.ui.theme.Purple80
import grpc_code_gen.chat.message.messageGrpcKt
import grpc_code_gen.chat.message.messageRequest
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.*
import java.time.LocalDateTime


@Composable
fun ChatPageBottomBar(
    /*sendTextMessage: (TextFieldValue) -> Unit,*/
) {
    var text by remember { mutableStateOf("") }
    val ctx = LocalContext.current
    val messageScreenViewModelSingleton = MessageScreenViewModelSingleton()

    fun messageinsertOne(message: Message) {
        CoroutineScope(Dispatchers.IO).launch {
            val s = MessageDbSingleton(ctx)
            s.messageDao().insertOne(
                message
            )
//            s.close()
        }
    }


    val host = "10.0.2.2"
    val port = 8980

    val channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()

    val stub = messageGrpcKt.messageCoroutineStub(channel)

    var data: Message

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Purple80)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            label = { Text(text = "Input something") },//文本框未输入时显示的文本
            onValueChange = { text = it })

        Button(
            onClick =
            {
                val message = Message(
                    uid = user.id,
                    is_me = true,
                    from = user.id,
                    from_name = "张添羽",
                    to = chat.uid,
                    to_name = chat.name,
                    chat_type = 1,
                    msg_type = 1,
                    msg = text,
                    send_time = LocalDateTime.now(),
                    send_status = 1
                )
                runBlocking {
                    data = withContext(Dispatchers.IO) {
                        val messagereply = stub.find(
                            request = messageRequest
                            {
                                msgId = message.msg_id
                                uid = message.uid
                                isMe = !(message.is_me)
                                from = message.to
                                fromName = message.from_name
                                to = message.from
                                toName = message.to_name
                                chatType = message.chat_type
                                msgType = message.msg_type
                                msg = message.msg
                                sendTime = message.send_time.toString()
                                sendStatus = message.send_status
                            })
                        val dataSwap = Message(
                            msg_id = messagereply.msgId,
                            uid = messagereply.uid,
                            is_me = messagereply.isMe,
                            from = messagereply.from,
                            from_name = messagereply.fromName,
                            to = messagereply.to,
                            to_name = messagereply.toName,
                            chat_type = messagereply.chatType,
                            msg_type = messagereply.msgType,
                            msg = messagereply.msg,
                            send_time = LocalDateTime.now(),
                            send_status = messagereply.sendStatus
                        )

                        return@withContext dataSwap
                    }
                }
                messageinsertOne(message)
                messageinsertOne(data)
                messageScreenViewModelSingleton.add(message)
                messageScreenViewModelSingleton.add(data)
            }) {
            Text(text = "发送")
        }
    }

}

@Preview
@Composable
fun PreviewChatChatPageBottomBar() {
    ChatPageBottomBar()
}