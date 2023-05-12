package ru.kekulta.listapp.features.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import org.koin.androidx.compose.koinViewModel
import ru.kekulta.listapp.features.list.domain.models.ListState
import ru.kekulta.listapp.features.list.domain.presentation.ListViewModel
import ru.kekulta.listapp.shared.models.ArtItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: ListViewModel = koinViewModel(), navigateToDetails: (ArtItem) -> Unit) {
    val state = viewModel.state.collectAsState()
    val query = remember { mutableStateOf("") }
    val active = remember { mutableStateOf(false) }

    fun search(query: String) {
        viewModel.searchQuery(query)
    }

    Column {
        SearchBar(
            query = query.value,
            onQueryChange = { query.value = it },
            onSearch = {
                active.value = false
                search(it)
            },
            active = active.value,
            onActiveChange = { active.value = it },
            modifier = Modifier.fillMaxWidth()
        ) {}

        when (state.value.listState) {
            is ListState.Error -> {
                Text(text = "Error: ${(state.value.listState as ListState.Error).cause}")
            }

            ListState.Loading -> {
                Text(text = "Loading...")
            }

            is ListState.ShowList -> {

                if (state.value.list.isEmpty()) {
                    Text(text = "No results")
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 128.dp),
                    ) {
                        items(state.value.list) {
                            ArtImage(it, navigateToDetails)
                        }
                        header {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = { viewModel.prevPage() },
                                    enabled = state.value.paginationState.current > 1
                                ) {
                                    Text(text = "<")
                                }
                                Text(text = "${state.value.paginationState.current}/${state.value.paginationState.total}")
                                Button(
                                    onClick = { viewModel.nextPage() },
                                    enabled = state.value.paginationState.current < state.value.paginationState.total
                                ) {
                                    Text(text = ">")
                                }

                            }
                        }
                    }
                }
            }
        }

    }
}




