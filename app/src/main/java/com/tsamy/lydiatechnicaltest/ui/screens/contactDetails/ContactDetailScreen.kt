package com.tsamy.lydiatechnicaltest.ui.screens.contactDetails

import android.content.Intent
import android.content.Intent.createChooser
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tsamy.lydiatechnicaltest.R
import com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.logic.ContactDetailEvent
import com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.logic.ContactDetailViewModel
import com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.views.ContactDetail


@Composable
fun ContactDetailScreen(
    navController: NavController,
    viewModel: ContactDetailViewModel = hiltViewModel(),
) {

    val contact by viewModel.contactFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is ContactDetailEvent.NavigateBack -> navController.popBackStack()
                is ContactDetailEvent.NavigateToMail -> {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "message/rfc822"
                        putExtra(Intent.EXTRA_EMAIL, event.email)
                    }
                    context.startActivity(
                        createChooser(
                            intent,
                            context.getString(R.string.send_email_title)
                        ), null
                    )
                }

                is ContactDetailEvent.NavigateToPhone -> {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + event.number))
                    context.startActivity(intent)
                }

                is ContactDetailEvent.NavigateToMap -> {
                    val uri: String = String.format("geo:0,0?q=%s", event.address)
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    context.startActivity(intent)
                }
            }
        }
    }
    ContactDetail(contact = contact, onInteraction = viewModel::onInteraction)
}