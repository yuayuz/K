package com.k.data.viewmodel

import androidx.lifecycle.ViewModel
import com.k.data.db.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MessageScreenState(
    var list: List<Message>,
)

private fun sort(list: List<Message>) =
    list.sortedBy { it.send_time}

class MessageViewModel() :ViewModel(){

    private val mutState = MutableStateFlow(
        MessageScreenState(
            listOf(),
//            ctx
        )
    )

    val state = mutState.asStateFlow()


//
//    suspend fun getAll(): List<Message>? {
//        val s = MessageDbSingleton(state.value.ctx)
//        val data = s.messageDao().getAll()
//        s.close()
//        if (data != null) {
//            mutState.value.list=data
//        }
//        return data
//    }

    fun reset(list: List<Message>) {
        mutState.value = MessageScreenState(
            sort(list)
        )
    }


    fun add(data: Message) {
        val new = mutState.value.list.plus(data)
        reset(new)
    }

}

object MessageScreenViewModelSingleton {
    private var viewModel = MessageViewModel()

    fun reset() = viewModel.reset(listOf())

    operator fun invoke() = viewModel

}