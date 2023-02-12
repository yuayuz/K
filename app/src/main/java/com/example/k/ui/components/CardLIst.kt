package com.example.k.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k.data.ui.listdata
import com.example.k.ui.components.ContactItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun <T> CardList(
    itemFetcher: () -> List<T>,
    itemRender: @Composable (T) -> Unit,
) {
    var itemList by remember { mutableStateOf(itemFetcher()) }

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        itemList = itemFetcher()
        delay(500)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(state)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            itemList.forEach {
                itemRender(it)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        PullRefreshIndicator(
            refreshing = refreshing,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}

@Preview
@Composable
fun CardListPreview() {
    CardList(
        itemFetcher = {
            List(4) {
                listdata(
                    12384, "Hola", "Just hello world!"
                )
            }
        },
        itemRender = @Composable {
            ContactItem({}, it)
        }
    )
}