package com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.views

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tsamy.lydiatechnicaltest.R
import com.tsamy.lydiatechnicaltest.domain.entity.Address
import com.tsamy.lydiatechnicaltest.domain.entity.Contact
import com.tsamy.lydiatechnicaltest.ui.components.Header
import com.tsamy.lydiatechnicaltest.ui.components.ImageUrl
import com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.logic.ContactDetailInteraction
import com.tsamy.lydiatechnicaltest.ui.theme.Palette
import com.tsamy.lydiatechnicaltest.ui.theme.Typography
import com.tsamy.lydiatechnicaltest.utils.stringToDate

@Composable
fun ContactDetail(
    contact: Contact?,
    onInteraction: (ContactDetailInteraction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Color.White),
    ) {
        Header(
            modifier = Modifier,
            titleId = R.string.contact_details_title,
            onBackPressed = { onInteraction(ContactDetailInteraction.OnBackPressed) }
        )
        contact?.let {
            Content(
                modifier = Modifier.padding(top = 20.dp),
                contact = it,
                onInteraction = onInteraction
            )
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    contact: Contact,
    onInteraction: (ContactDetailInteraction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ImageUrl(
            modifier = Modifier
                .clip(RoundedCornerShape(75.dp))
                .background(Palette.Gray)
                .size(150.dp)
                .align(Alignment.CenterHorizontally),
            imageUrl = contact.largeImageUrl,
            contentScale = ImageUrl.Scale.Fit,
            contentDescriptionId = R.string.list_contacts_title
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = Typography.TitleMMedium,
            text = "${contact.firstname} ${contact.lastname}"
        )
        Info(modifier = Modifier, label = R.string.dob_label, value = stringToDate(contact.dob)) {}
        Info(modifier = Modifier, label = R.string.email_label, value = contact.email) {
            onInteraction(ContactDetailInteraction.OnEmailPressed)
        }
        Info(modifier = Modifier, label = R.string.phone_label, value = contact.phone) {
            onInteraction(ContactDetailInteraction.OnPhonePressed(contact.phone))
        }
        Info(modifier = Modifier, label = R.string.cell_label, value = contact.cell) {
            onInteraction(ContactDetailInteraction.OnPhonePressed(contact.cell))
        }
        Address(modifier = Modifier, contact.address) {
            onInteraction(ContactDetailInteraction.OnAddressPressed)
        }
    }
}

@Composable
private fun Info(modifier: Modifier, @StringRes label: Int, value: String, onClick: () -> Unit) {
    Row(modifier = modifier.clickable { onClick() }) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = label),
            style = Typography.ParagraphLLight
        )
        Text(text = value, style = Typography.ParagraphLRegular)
    }
}

@Composable
private fun Address(modifier: Modifier, address: Address, onClick: () -> Unit) {
    Row(modifier = modifier.clickable { onClick() }) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.address_label),
            style = Typography.ParagraphLLight
        )
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${address.number} ${address.street}",
                style = Typography.ParagraphLRegular
            )
            Text(
                text = "${address.city} ${address.postCode}",
                style = Typography.ParagraphLRegular
            )
            Text(text = address.country, style = Typography.ParagraphLRegular)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val contact = Contact(
        1,
        "thomas@samy.fr",
        "Thomas",
        "SAMY",
        "1999-11-11T12:12:12.500Z",
        Address(
            10,
            "rue du stade",
            "Paris",
            "France",
            "0.0",
            "0.0",
            "75016"
        ),
        "0501020304",
        "0601020304",
        "",
        ""
    )
    ContactDetail(contact = contact) {}
}