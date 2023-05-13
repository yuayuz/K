package com.k.data.viewmodel

import androidx.lifecycle.ViewModel
import com.k.data.db.Conversation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChatListScreenState(
    val list: List<Conversation>
)

private fun sort(list: List<Conversation>) =
    list.sortedBy { it.last_time}.reversed()

class ChatListViewModel :ViewModel(){

    private val mutState = MutableStateFlow(
        ChatListScreenState(
            listOf()
        )
    )

    val state = mutState.asStateFlow()

    fun reset(list: List<Conversation>) {
        mutState.value = ChatListScreenState(
            sort(list)
        )
    }


    fun add(data: Conversation) {
        val new = mutState.value.list.plus(data)
        reset(new)
    }

}

object ChatListScreenViewModelSingleton {
    private var viewModel = ChatListViewModel()

    fun reset() = viewModel.reset(listOf())

    operator fun invoke() = viewModel
}
