package com.tsamy.lydiatechnicaltest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tsamy.lydiatechnicaltest.R
import com.tsamy.lydiatechnicaltest.ui.screens.home.logic.HomeInteraction
import com.tsamy.lydiatechnicaltest.ui.theme.Palette
import com.tsamy.lydiatechnicaltest.ui.theme.Typography

object ContactItem {

    /**
     * Contact data
     */
    data class Model(
        val id: Int,
        val imageUrl: String,
        val name: String,
        val phone: String
    )

}

@Composable
fun ContactItem(
    modifier: Modifier,
    model: ContactItem.Model,
    onInteraction: (HomeInteraction) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Palette.Gray100)
            .padding(12.dp)
            .clickable(interactionSource, indication = null) {
                onInteraction(HomeInteraction.OnContactSelected(model.id))
            }
    ) {
        ImageUrl(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(52.dp),
            imageUrl = model.imageUrl,
            contentScale = ImageUrl.Scale.Fit,
            contentDescriptionId = R.string.contact_image
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = model.name, style = Typography.ParagraphM
            )
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Image(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = stringResource(id = R.string.phone_icon)
                )
                Text(
                    text = model.phone, style = Typography.ParagraphMMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.background(Palette.Transparent)) {
        ContactItem(
            modifier = Modifier,
            model = ContactItem.Model(0, "", "Thomas SAMY", "(123)-456-7890"),
            onInteraction = {}
        )
    }
}