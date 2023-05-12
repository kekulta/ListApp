package ru.kekulta.listapp.features.list.domain.models

import ru.kekulta.listapp.shared.models.ArtItem

data class ListScreenState(
    val listState: ListState = ListState.ShowList(),
    val paginationState: PaginationState = PaginationState(),
    val list: List<ArtItem> = listOf()
)