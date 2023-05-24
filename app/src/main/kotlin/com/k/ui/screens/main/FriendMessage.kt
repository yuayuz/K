package com.k.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k.data.db.ContactPerson
import com.k.data.db.ContactPersonDbSingleton
import com.k.data.db.Conversation
import com.k.data.db.ConversationDbSingleton
import com.k.data.newFriend
import com.k.data.user
import grpc_code_gen.friend.friendMessage.newFriendMessageRequest
import grpc_code_gen.friend.newFriend.friendMessageGrpcKt
import grpc_code_gen.friend.newFriend.newFriendRequest
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FriendMessageScreen(
    navTONewFriend: () -> Unit,
    navToChatList: () -> Unit
) {

    val host = "10.0.2.2"
    val port = 8980

    val channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()

    val stub = friendMessageGrpcKt.friendMessageCoroutineStub(channel)

    var name by remember {
        mutableStateOf("")
    }
    var uid by remember {
        mutableStateOf(Long.MAX_VALUE)
    }
    var sex by remember {
        mutableStateOf(0)
    }
    var birthdayDate by remember {
        mutableStateOf("")
    }
    try {
        CoroutineScope(Dispatchers.IO).launch {
            val data = stub.find(request = newFriendRequest {
                id = newFriendMessageRequest {
                    /*if (id != null) {
                        this.id = 33333
                    }*/
                    id = newFriend.uid
                }
            }).message
            name = data.userName
            uid = data.uid
            sex = data.sex
            birthdayDate = data.birthdayDate
        }
    } catch (e: Throwable) {
        val thread = Thread.currentThread()
        thread.uncaughtExceptionHandler?.uncaughtException(thread, e)

    }

    val ctx = LocalContext.current
    fun contactPersonInsertOne(contactPerson: ContactPerson) {
        CoroutineScope(Dispatchers.IO).launch {
            val s = ContactPersonDbSingleton(ctx)
            s.contactPersonDao().insertOne(
                contactPerson
            )
            s.close()
        }
    }

    fun conversationInsertOne(conversation: Conversation) {
        CoroutineScope(Dispatchers.IO).launch {
            val s = ConversationDbSingleton(ctx)
            s.conversationDao().insertOne(
                conversation
            )
            s.close()
        }
    }

    Box(
        Modifier.fillMaxSize()
//            .padding(contentPadding)
    ) {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom,
        ) {
            IconButton(
                modifier = Modifier
                    .padding(20.dp),
                onClick = { navTONewFriend() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }

        Column {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .background(Color.White)
                    .padding(40.dp)
                    .fillMaxWidth()
            ) {

                Text(text = name)
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = uid.toString())
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = when (sex) {
                        0 -> "男"
                        1 -> "女"
                        else -> "信息错误"
                    }

                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = birthdayDate)
                Spacer(modifier = Modifier.height(50.dp))


                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        val contactPerson = ContactPerson(
                            uid = uid,
                            UserId= user.id,
                            user_name = name,
                            birthday_date = LocalDateTime.now(),
                            relation = 1,
                            sex = 1
                        )
                        val conversation = Conversation(
                            uid=uid,
                            ChatId=user.id,
                            from =user.id,
                            to=uid,
                            last_msg="",
                            last_msg_id=0,
                            chat_name=name,
                            last_user_name=user.name,
                            last_time= LocalDateTime.now(),
                            chat_type=0,
                            msg_type=0,
                            unread_count=0
                        )
                        contactPersonInsertOne(contactPerson)
                        conversationInsertOne(conversation)
                        navToChatList()
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe)),
                    contentPadding = PaddingValues(12.dp, 16.dp)
                ) {
                    androidx.compose.material.Text("确定", color = Color.White, fontSize = 18.sp)
                }
            }
        }

    }
}

@Preview
@Composable
fun Preview() {
    FriendMessageScreen({}, {})
}

