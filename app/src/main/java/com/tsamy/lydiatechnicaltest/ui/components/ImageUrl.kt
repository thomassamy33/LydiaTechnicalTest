package com.tsamy.lydiatechnicaltest.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest


object ImageUrl {

    /**
     * Content scale type
     */
    enum class Scale {
        Fit, Crop
    }

}

@Composable
fun ImageUrl(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ImageUrl.Scale,
    @StringRes contentDescriptionId: Int
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(id = contentDescriptionId),
        imageLoader = ImageLoader(LocalContext.current),
        modifier = modifier.fillMaxSize(),
        contentScale = when (contentScale) {
            ImageUrl.Scale.Fit -> ContentScale.Fit
            ImageUrl.Scale.Crop -> ContentScale.Crop
        },
    )
}