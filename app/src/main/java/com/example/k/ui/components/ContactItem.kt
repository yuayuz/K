package com.example.k.ui.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k.data.ui.listdata

@SuppressLint("SimpleDateFormat")
@Composable
fun ContactItem(
    navToChat:(Long)->Unit,
    data: listdata
) {
    Card(
        modifier = Modifier
            .clickable { navToChat(data.id) },
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
                    text = data.name,
                    fontSize = 20.sp
                )
                Text(
                    text = data.id.toString(),
                    fontSize = 15.sp
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewContactItem(){
    ContactItem(
        navToChat ={} ,
        data = listdata(
            12345,
            "Tom",
            "hello"
        )
    )
}