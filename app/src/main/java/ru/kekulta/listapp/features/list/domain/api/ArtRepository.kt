package ru.kekulta.listapp.features.list.domain.api

import ru.kekulta.listapp.features.list.data.dto.ArtSearchRequest
import ru.kekulta.listapp.shared.models.ArtSearchResponse
import ru.kekulta.listapp.shared.models.Resource

interface ArtRepository {
    suspend fun searchArts(request: ArtSearchRequest): Resource<ArtSearchResponse>
}