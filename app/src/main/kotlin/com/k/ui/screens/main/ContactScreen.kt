package com.k.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.k.data.db.ContactPerson
import com.k.data.viewmodel.ConversationScreenViewModelSingleton
import com.k.ui.components.CardList
import com.k.ui.components.ContactItem
import java.util.*


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ContactScreen(
    contentPadding: PaddingValues,
    navToChat: (Long) -> Unit
) {
    val viewModel = ConversationScreenViewModelSingleton()
    val uiState by viewModel.state.collectAsState()

    /*val data =
        ContactPerson(
            uid = 123456,
            user_name = "asd",
            birthday_date = Date(2015 - 1900, 11, 30, 23, 59, 59),
            relation = 1,
            sex = 1
        )

    viewModel.add(data)*/
    var date by remember {
        mutableStateOf(listOf<ContactPerson>())
    }
   /* val ctx = LocalContext.current
    CoroutineScope(Dispatchers.IO).launch{
        val dao= ContactPersonDbSingleton(ctx)
        date= dao.contactPersonDao().getAll()!!
    }*/


    Column(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(horizontal = 10.dp)
    ) {
        CardList(
            itemFetcher = {
                ContactDataList
            },
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

