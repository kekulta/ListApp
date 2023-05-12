package ru.kekulta.listapp.features.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.map
import org.koin.androidx.compose.koinViewModel
import ru.kekulta.listapp.features.list.domain.models.ListState
import ru.kekulta.listapp.features.list.domain.presentation.ListViewModel
import ru.kekulta.listapp.features.list.ui.ArtImage
import ru.kekulta.listapp.shared.models.ArtItem

private const val UNKNOWN = "Unknown"

@Composable
fun Details(item: ArtItem?, viewModel: ListViewModel = koinViewModel()) {
    val state = rememberScrollState()

    item?.let { artItem ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state)
        ) {
            ArtImage(artItem, null)
            Text(text = "Artist: ${artItem.artist ?: UNKNOWN}")
            Text(text = "Place of origin: ${artItem.placeOfOrigin ?: UNKNOWN}")
            Text(text = "Date start: ${artItem.dateStart ?: UNKNOWN}")
            Text(text = "Date end: ${artItem.dateEnd ?: UNKNOWN}")
        }
    }

}