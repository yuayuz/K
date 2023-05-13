package com.k.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.k.data.db.ContactPerson
import com.k.data.db.ContactPersonDbSingleton
import com.k.data.user
import com.k.data.viewmodel.ConversationScreenViewModelSingleton
import com.k.ui.components.CardList
import com.k.ui.components.ContactItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.*


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ContactScreen(
    contentPadding: PaddingValues,
    navToChat: (Long) -> Unit,
    showMistake:()->Unit
) {

    val ctx = LocalContext.current
    val d :List<ContactPerson>
    runBlocking{
        d = withContext(Dispatchers.IO){
            val s = ContactPersonDbSingleton(ctx)
            return@withContext s.contactPersonDao().getOneByUserId(user.id)!!
        }
    }
    val l = ConversationScreenViewModelSingleton
    l().reset(d)
    val uiState by l().state.collectAsState()



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
                uiState.list
            },
            itemRender = {
                val id=it.uid
                ContactItem(
                    {navToChat(id)},
                    it,
                    showMistake = { showMistake() }
                )
            }
        )
    }
}



