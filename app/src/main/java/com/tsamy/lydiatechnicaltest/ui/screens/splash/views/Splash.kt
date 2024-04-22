package com.tsamy.lydiatechnicaltest.ui.screens.splash.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tsamy.lydiatechnicaltest.R
import com.tsamy.lydiatechnicaltest.ui.screens.splash.logic.SplashInteraction
import com.tsamy.lydiatechnicaltest.ui.theme.Palette
import com.tsamy.lydiatechnicaltest.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun Splash(
    onInteraction: (SplashInteraction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.SplashGradient),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.splash_name_label),
                style = Typography.TitleM
            )
            Text(
                text = stringResource(id = R.string.splash_author_label),
                style = Typography.SubtitleL
            )
            Image(
                modifier = Modifier.width(80.dp),
                painter = painterResource(id = R.drawable.lydia_logo),
                contentDescription = stringResource(id = R.string.lydia_logo)
            )
        }
    }
    LaunchedEffect(Unit) {
        delay(2000)
        onInteraction(SplashInteraction.OnDelayEnded)
    }
}

@Preview
@Composable
private fun Preview() {
    Splash(onInteraction = {})
}