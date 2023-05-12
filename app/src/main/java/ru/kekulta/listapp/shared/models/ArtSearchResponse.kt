package ru.kekulta.listapp.shared.models

import ru.kekulta.listapp.features.list.domain.models.PaginationState

data class ArtSearchResponse(val arts: List<ArtItem>, val paginationState: PaginationState)