package com.k.ui.screens.main

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FriendMessageScreen(
    navTONewFriend:()->Unit,
    navToChatList: () -> Unit
<<<<<<< HEAD
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

=======
){
>>>>>>> parent of 512caaf (chat)
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

        Column() {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .background(Color.White)
                    .padding(40.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "姓名")
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "id")
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "生日日期")

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
<<<<<<< HEAD
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
                        channel.shutdown()
=======
>>>>>>> parent of 512caaf (chat)
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

@Composable
@Preview
fun PreviewFriendMessage(){
    FriendMessageScreen ({},{})
}