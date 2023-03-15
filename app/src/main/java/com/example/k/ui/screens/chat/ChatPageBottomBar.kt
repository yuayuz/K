package com.example.k.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.k.ui.theme.Purple80


@Composable
fun ChatPageBottomBar(
    /*sendTextMessage: (TextFieldValue) -> Unit,*/
) {
    var text by remember { mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(Purple80)
                        .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            label = { Text(text = "Input something") },//这里label为文本框未输入时显示的文本
            onValueChange = { text = it })

        Button(onClick = { }) {
            Text(text = "发送")
        }
    }

}

@Preview
@Composable
fun PreviewChatChatPageBottomBar() {
    ChatPageBottomBar()
}