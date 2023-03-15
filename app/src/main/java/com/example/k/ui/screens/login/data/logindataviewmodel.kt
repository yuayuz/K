package com.example.k.ui.screens.login.data

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.k.data.db.Account
import com.example.k.data.db.AccountDbSingleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class logindataviewvieewmodel(ctx: Context) : ViewModel() {

    /*var text by remember{ mutableStateOf("") }
    fun DB(ctx: Context,text:String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val s = AccountDbSingleton(ctx)
            s.accountDao().insertone(Account(1, "y", "1"))
            val d = s.accountDao().getone(1)!!.password
            text = d
        }
    }*/


}