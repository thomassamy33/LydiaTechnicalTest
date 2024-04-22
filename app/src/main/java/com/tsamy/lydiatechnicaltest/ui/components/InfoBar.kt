package com.tsamy.lydiatechnicaltest.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tsamy.lydiatechnicaltest.ui.theme.Palette

@Composable
fun InfoBar(modifier: Modifier, @StringRes stringId: Int) {
    Box(
        modifier = modifier
            .background(Palette.Error)
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            text = stringResource(id = stringId),
            textAlign = TextAlign.Center,
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .align(Alignment.BottomCenter),
            color = Palette.Gray
        )
    }
}