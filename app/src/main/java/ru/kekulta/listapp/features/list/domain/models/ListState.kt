package ru.kekulta.listapp.features.list.domain.models

import ru.kekulta.listapp.shared.models.ArtItem

sealed class ListState {
    object Loading : ListState()
    class Error(val cause: String = "UnknownError") : ListState()
    class ShowList() : ListState()
}