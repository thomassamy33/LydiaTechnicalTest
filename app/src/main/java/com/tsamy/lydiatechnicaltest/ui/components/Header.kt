package com.tsamy.lydiatechnicaltest.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tsamy.lydiatechnicaltest.R
import com.tsamy.lydiatechnicaltest.ui.theme.Palette
import com.tsamy.lydiatechnicaltest.ui.theme.Typography

@Composable
fun Header(modifier: Modifier, @StringRes titleId: Int, onBackPressed: (() -> Unit)?) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Palette.Gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            onBackPressed?.let {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable { it() },
                    text = stringResource(id = R.string.back_button_label),
                    style = Typography.ParagraphXS,
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = stringResource(id = titleId),
                style = Typography.ParagraphLBold,
                textAlign = TextAlign.Center
            )
            Image(
                modifier = Modifier
                    .width(60.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.lydia_logo),
                contentDescription = stringResource(id = R.string.lydia_logo)
            )
        }
        HorizontalDivider(
            color = Palette.Black,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}
