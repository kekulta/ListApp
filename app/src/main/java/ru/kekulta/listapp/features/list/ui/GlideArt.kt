package ru.kekulta.listapp.features.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.kekulta.listapp.shared.models.ArtItem


private const val IMAGE_DOWNLOAD_URL = "https://www.artic.edu/iiif/2/%s/full/843,/0/default.jpg"

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArtImage(item: ArtItem, navigateToDetails: ((ArtItem) -> Unit)?) {
    Column(modifier = Modifier.clickable { navigateToDetails?.invoke(item) }) {
        GlideImage(
            model = IMAGE_DOWNLOAD_URL.format(item.imageId),
            contentDescription = item.title
        )
        Text(text = item.title)
    }
}