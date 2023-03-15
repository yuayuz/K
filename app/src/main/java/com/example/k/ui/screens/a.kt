package com.example.k.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.k.data.db.Account
import com.example.k.data.db.AccountDbSingleton
import com.example.k.data.db.ContactPerson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun dba(){
    val ctx = LocalContext.current
    var text by remember{ mutableStateOf("") }
    /*CoroutineScope(Dispatchers.IO).launch{
        val s= ContactPersonDbSingleton(ctx)
        s.contactpersondao().insertone(ContactPerson(
            uid = 1,
            user_name = "asd",
            birthday_date = Date(2015 - 1900, 11, 30, 23, 59, 59),
            relation = 1,
            sex = 1))
        val d=s.contactpersondao().getone(1)!!.user_name
        if (d != null) {
            text=d
        }
    }*/
    Column(
        Modifier.fillMaxSize()
    ) {
        Text(text = text)
        Text(text = "shdahfioajfoa")
    }

}

@Preview
@Composable
fun Previewdba(){
    dba()
}

