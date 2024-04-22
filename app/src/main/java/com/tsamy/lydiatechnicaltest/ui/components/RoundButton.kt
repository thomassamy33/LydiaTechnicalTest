package com.tsamy.lydiatechnicaltest.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(modifier: Modifier, @StringRes stringId: Int, onClick: () -> Unit) {
    Box(modifier = modifier
        .padding(30.dp)
        .size(75.dp)
        .clip(RoundedCornerShape(37.5.dp))
        .clickable {
            onClick()
        }
    ) {
        Text(text = stringResource(id = stringId), modifier = Modifier.align(Alignment.Center))
    }
}