package com.example.k.data.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.k.data.db.Account
import com.example.k.data.db.AccountDbSingleton
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class LoginListScreenState(
    val data: Account,
    val ctx: Context,
)


class LoginScreenViewModel(ctx: Context, account: Account) : ViewModel() {
    private val mutState = MutableStateFlow(
        LoginListScreenState(
            account,
            ctx,
        )
    )

    private val state = mutState.asStateFlow()

    fun insertOne() {
        CoroutineScope(Dispatchers.IO).launch {
            val s = AccountDbSingleton(state.value.ctx)
            s.accountDao().insertOne(
                account = state.value.data
            )
            s.close()
        }
    }

    suspend fun queryOne():Int {
//        CoroutineScope(Dispatchers.IO).launch {
                val s = AccountDbSingleton(state.value.ctx)
                val data=s.accountDao().getOne(state.value.data.id)
                s.close()
                /*rt = if (data==null){
                    0
                } else if (state.value.data.password == data.password){
                    1
                } else{
                    2
                }*/
                 val rt= when{
                    data==null->0
                    state.value.data.password==data.password->1
                    else->2
//                }
        }
        return rt
    }

    fun revisePassword(){
        CoroutineScope(Dispatchers.IO).launch {
            val s = AccountDbSingleton(state.value.ctx)
            s.accountDao().updateOne(
                account = state.value.data
            )
            s.close()
        }
    }

}

object CommentListScreenViewModelSingleton {
//    private var viewModel = LoginScreenViewModel()

    operator fun invoke(ctx: Context, account: Account) = LoginScreenViewModel(ctx,account)
}