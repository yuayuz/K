package com.k.data.viewmodel

import androidx.lifecycle.ViewModel
import com.k.data.db.ContactPerson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ConversationScreenState(
    var list: List<ContactPerson>,
)

private fun sort(list: List<ContactPerson>) =
    list.sortedBy { it.user_name}.reversed()

class ConversationViewModel() :ViewModel(){

    private var mutState = MutableStateFlow(
        ConversationScreenState(
            listOf(),
        )
    )

    val state = mutState.asStateFlow()



    /*suspend fun getAll(): List<ContactPerson>? {
        val s = ContactPersonDbSingleton(state.value.ctx)
        val data = s.contactPersonDao().getAll()
        s.close()
        if (data != null) {
            mutState.value.list=data
        }
        return data
    }*/

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

    operator fun invoke() = viewModel
}
