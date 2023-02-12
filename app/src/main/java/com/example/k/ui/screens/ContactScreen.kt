package com.example.k.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k.ui.components.CardList
import com.example.k.data.ui.listdata
import com.example.k.ui.components.ContactItem


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
    listdata(
        1234567,
        "asdfg",
        "人之初，性本善。性相近，习相远。\n" +
                "苟不教，性乃迁。教之道，贵以专。"
    )
}

