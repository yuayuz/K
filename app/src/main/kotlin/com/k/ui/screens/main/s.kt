package com.k.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.k.data.db.ContactPerson
import com.k.data.db.ContactPersonDbSingleton
import com.k.data.viewmodel.ConversationScreenViewModelSingleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun s() {
    val ctx = LocalContext.current
    val viewModel = ConversationScreenViewModelSingleton()
    val uiState by viewModel.state.collectAsState()
    val data =
        ContactPerson(
            uid = 123456,
            user_name = "asd",
            birthday_date = Date(2015 - 1900, 11, 30, 23, 59, 59),
            relation = 1,
            sex = 1
        )
    CoroutineScope(Dispatchers.IO).launch {
        val dao= ContactPersonDbSingleton(ctx =ctx )
        dao.contactPersonDao().insertOne(data)
    }
    viewModel.add(data)
}

@Preview
@Composable
fun Previews(){
    s()
}