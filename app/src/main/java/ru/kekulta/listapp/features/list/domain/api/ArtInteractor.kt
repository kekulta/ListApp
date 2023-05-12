package ru.kekulta.listapp.features.list.domain.api

import kotlinx.coroutines.flow.Flow
import ru.kekulta.listapp.features.list.data.dto.ArtSearchRequest
import ru.kekulta.listapp.shared.models.ArtSearchResponse
import ru.kekulta.listapp.shared.models.Resource

interface ArtInteractor {
    suspend fun searchArts(request: ArtSearchRequest): Flow<Resource<ArtSearchResponse>>
}