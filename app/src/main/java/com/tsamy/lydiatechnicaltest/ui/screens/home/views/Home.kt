package com.tsamy.lydiatechnicaltest.ui.screens.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tsamy.lydiatechnicaltest.R
import com.tsamy.lydiatechnicaltest.network.NetworkStatus
import com.tsamy.lydiatechnicaltest.ui.components.ContactItem
import com.tsamy.lydiatechnicaltest.ui.components.Header
import com.tsamy.lydiatechnicaltest.ui.components.InfoBar
import com.tsamy.lydiatechnicaltest.ui.components.RoundButton
import com.tsamy.lydiatechnicaltest.ui.screens.home.logic.HomeInteraction
import com.tsamy.lydiatechnicaltest.ui.theme.Palette
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun Home(
    contacts: LazyPagingItems<ContactItem.Model>,
    networkStatus: NetworkStatus,
    onInteraction: (HomeInteraction) -> Unit,
) {
    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Color.White),
    ) {
        Header(
            modifier = Modifier,
            titleId = R.string.list_contacts_title,
            onBackPressed = null
        )
        if (networkStatus != NetworkStatus.Connected) {
            InfoBar(modifier = Modifier, stringId = R.string.no_connection_label)
        }
        if (contacts.loadState.append is LoadState.Error) {
            InfoBar(modifier = Modifier, stringId = R.string.append_data_failed_label)
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(contacts.itemCount) { index ->
                    contacts[index]?.let { model ->
                        ContactItem(
                            modifier = Modifier,
                            model = model,
                            onInteraction = onInteraction
                        )
                    }
                }
            }
            if (lazyListState.canScrollBackward) {
                RoundButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    color = Palette.Gray,
                    stringId = R.string.top_label,
                    onClick = {
                        coroutineScope.launch {
                            lazyListState.scrollToItem(0)
                        }
                    }
                )
            }
            if (contacts.loadState.append is LoadState.Error) {
                RoundButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart),
                    color = Palette.Error,
                    stringId = R.string.retry_label,
                    onClick = {
                        coroutineScope.launch {
                            contacts.retry()
                        }
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    val state = flowOf(PagingData.empty<ContactItem.Model>()).collectAsLazyPagingItems()
    Home(contacts = state, networkStatus = NetworkStatus.Unknown, onInteraction = {})
}