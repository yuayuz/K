package com.k.data.viewmodel

import androidx.lifecycle.ViewModel
import com.k.data.db.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MessageScreenState(
    val list: List<Message>
)

private fun sort(list: List<Message>) =
    list.sortedBy { it.send_time}.reversed()

class MessageViewModel :ViewModel(){

    private val mutState = MutableStateFlow(
        MessageScreenState(
            listOf()
        )
    )

    val state = mutState.asStateFlow()

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

    operator fun invoke() = MessageViewModel()
}