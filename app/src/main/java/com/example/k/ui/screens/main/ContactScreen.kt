package com.example.k.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.k.data.db.ContactPerson
import com.example.k.ui.components.CardList
import com.example.k.ui.components.ContactItem
import java.util.*


@Composable
fun ContactScreen(
    contentPadding: PaddingValues,
    navToChat: (Long) -> Unit
){
    Column(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(horizontal = 10.dp)
    ) {
        CardList(
            itemFetcher = { ContactDataList },
            itemRender = {
                ContactItem(navToChat, it)
            }
        )
    }
}

val ContactDataList = List(8) {
    ContactPerson(
        uid = 123456,
        user_name = "asd",
        birthday_date = Date(2015 - 1900, 11, 30, 23, 59, 59),
        relation = 1,
        sex = 1
    )
}

