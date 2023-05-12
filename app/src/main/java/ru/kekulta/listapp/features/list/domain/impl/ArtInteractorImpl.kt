package ru.kekulta.listapp.features.list.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent.inject
import ru.kekulta.listapp.features.list.data.dto.ArtSearchRequest
import ru.kekulta.listapp.features.list.domain.api.ArtInteractor
import ru.kekulta.listapp.features.list.domain.api.ArtRepository
import ru.kekulta.listapp.shared.models.ArtSearchResponse
import ru.kekulta.listapp.shared.models.Resource

class ArtInteractorImpl(private val repository: ArtRepository) : ArtInteractor {

    override suspend fun searchArts(request: ArtSearchRequest): Flow<Resource<ArtSearchResponse>> {
        return flow {
            emit(repository.searchArts(request))
        }
    }
}