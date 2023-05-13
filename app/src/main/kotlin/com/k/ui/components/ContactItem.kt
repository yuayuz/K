package com.k.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k.data.db.ContactPerson
import com.k.data.db.MessageDbSingleton
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun ContactItem(
    navToChat:()->Unit,
    data: ContactPerson,
    showMistake:()->Unit
) {

    val ctx = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    fun tryDoEdit() = coroutineScope.launch {
        val dao = MessageDbSingleton(ctx).messageDao()
        if (dao.maybe(data.uid) != null)
            navToChat()
        else
            navToChat()
    }

    Card(
        modifier = Modifier
            .clickable { tryDoEdit() },
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){

            Image(
                modifier=Modifier.size(50.dp),
                imageVector = Icons.Default.Person,
                contentDescription = "联系人"
            )

            Column{
                Text(
                    text = data.user_name,
                    fontSize = 20.sp
                )
                Text(
                    text = data.uid.toString(),
                    fontSize = 15.sp
                )
            }
        }

    }
}

