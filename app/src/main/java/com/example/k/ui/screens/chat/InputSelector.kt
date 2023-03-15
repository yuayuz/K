package com.example.k.ui.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mood
import androidx.compose.material.icons.outlined.Topic
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class InputSelector {
    NONE,
    EMOJI,
    Picture,
}

@Composable
fun InputSelector(
    currentInputSelector: InputSelector,
    onInputSelectorChange: (InputSelector) -> Unit,
    sendMessageEnabled: Boolean,
    onMessageSent: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, top = 8.dp, end = 14.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InputSelectorButton(
            icon = Icons.Outlined.Mood,
            selected = currentInputSelector == InputSelector.EMOJI,
            onClick = {
                onInputSelectorChange(InputSelector.EMOJI)
            }
        )
        InputSelectorButton(
            modifier = Modifier.padding(start = 16.dp),
            icon = Icons.Outlined.Topic,
            selected = currentInputSelector == InputSelector.Picture,
            onClick = {
                onInputSelectorChange(InputSelector.Picture)
            }
        )
        Spacer(modifier = Modifier.weight(weight = 1f))
        Text(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 20.dp))
                .then(
                    other = if (sendMessageEnabled) {
                        Modifier.background(color = MaterialTheme.colorScheme.primary)
                    } else {
                        Modifier.background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.46f))
                    }
                )
                .clickable(onClick = onMessageSent)
                .padding(horizontal = 18.dp, vertical = 6.dp),
            text = "Send",
            fontSize = 15.sp,
            color = Color.White
        )
    }
}


@Composable
private fun InputSelectorButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .size(size = 24.dp) ,
        onClick = onClick,
//        imageVector = icon,
//        tint = if (selected) {
//            MaterialTheme.colorScheme.primary
//        } else {
//            MaterialTheme.colorScheme.primary.copy(alpha = 0.46f)
//        },
//        contentDescription = null
    ){
        Row() {
            Image(
                imageVector = icon,
                contentDescription = null
            )
        }
    }
}

