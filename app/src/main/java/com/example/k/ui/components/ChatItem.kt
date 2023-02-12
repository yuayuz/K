package com.example.k.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Merge
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k.R
import com.example.k.data.ui.listdata
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun ChatItem(
    navToChat:(Long)->Unit,
    data: listdata
){

    Card(
        modifier = Modifier
            .clickable { navToChat(data.id) },
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(15.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {

                    Image(
                        modifier=Modifier.size(50.dp),
                        imageVector = Icons.Default.Sms ,
                        contentDescription = "chat"
                    )

                    Column(

                    ) {
                        Text(text = data.name)
                        Text(
                            text = data.body,
                            modifier = Modifier,
                            maxLines = 1 ,
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
fun PreviewChatItem(){
    ChatItem(
        navToChat = {},
        data = listdata(
            id = 123456,
            name = "asd",
            body = "你好！android."
        ) )
}