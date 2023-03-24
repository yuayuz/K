package com.k.data.viewmodel

import androidx.lifecycle.ViewModel
import com.k.data.db.ContactPerson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ConversationScreenState(
    var list: List<ContactPerson>
)

private fun sort(list: List<ContactPerson>) =
    list.sortedBy { it.user_name}.reversed()

class ConversationViewModel :ViewModel(){

    private var mutState = MutableStateFlow(
        ConversationScreenState(
            listOf()
        )
    )

    val state = mutState.asStateFlow()

    fun reset(list: List<ContactPerson>) {
        mutState.value = ConversationScreenState(
            sort(list)
        )
    }


    fun add(data: ContactPerson) {
        val new = mutState.value.list.plus(data)
        reset(new)
    }

}

object ConversationScreenViewModelSingleton {
    private var viewModel = ConversationViewModel()

    fun reset() = viewModel.reset(listOf())

    operator fun invoke() = ConversationViewModel()
}
