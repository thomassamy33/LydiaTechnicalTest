package com.tsamy.lydiatechnicaltest.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.tsamy.lydiatechnicaltest.ui.screens.ApplicationScreen
import com.tsamy.lydiatechnicaltest.ui.screens.home.logic.HomeEvent
import com.tsamy.lydiatechnicaltest.ui.screens.home.logic.HomeViewModel
import com.tsamy.lydiatechnicaltest.ui.screens.home.views.Home
import com.tsamy.lydiatechnicaltest.ui.screens.navigationInputArgument

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state = viewModel.contactsFlow.collectAsLazyPagingItems()
    val networkStatus by viewModel.networkStatusFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event
            .collect { event ->
                when (event) {
                    is HomeEvent.NavigateToContactDetail -> {
                        val route = ApplicationScreen.ContactDetail.route.replace(
                            "{$navigationInputArgument}",
                            event.id.toString()
                        )

                        navController.navigate(route)
                    }
                }
            }
    }

    Home(contacts = state, networkStatus = networkStatus, onInteraction = viewModel::onInteraction)
}