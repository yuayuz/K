package com.k.ui.screens.chat


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.k.data.chat
import com.k.data.db.Message
import com.k.data.db.MessageDbSingleton
import com.k.data.viewmodel.MessageScreenViewModelSingleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "StateFlowValueCalledInComposition"
)
@Composable
fun Chat(
    navController: NavController,
    id: Long
) {
    chat.uid = id
    val ctx = LocalContext.current
    val d: List<Message>
    runBlocking {
        d = withContext(Dispatchers.IO) {
            val s = MessageDbSingleton(ctx)
            return@withContext s.messageDao().getAllByUid(id)!!
        }
    }


    val l = MessageScreenViewModelSingleton
    l().reset(d)
    val uiState by l().state.collectAsState()

    ShowMessage(
        msgList = uiState.list
    )


}

