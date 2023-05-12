package ru.kekulta.listapp.features.list.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kekulta.listapp.features.list.data.dto.ArtSearchRequest
import ru.kekulta.listapp.features.list.data.dto.ArtSearchResponseDto
import ru.kekulta.listapp.features.list.data.dto.ArtworkSearchResponseDto
import ru.kekulta.listapp.features.list.data.dto.toItem
import ru.kekulta.listapp.features.list.data.dto.toPaginationState
import ru.kekulta.listapp.features.list.domain.api.ArtRepository
import ru.kekulta.listapp.shared.models.ArtItem
import ru.kekulta.listapp.shared.models.ArtSearchResponse
import ru.kekulta.listapp.shared.models.Resource
import ru.kekulta.listapp.shared.utils.HTTTPCodes

class ArtRepositoryImpl(private val networkClient: NetworkClient) : ArtRepository {

    override suspend fun searchArts(request: ArtSearchRequest): Resource<ArtSearchResponse> {
        var result: ArtSearchResponse? = null

        withContext(Dispatchers.IO) {
            val responseArts = networkClient.doRequest(request)
            if (responseArts.resultCode == HTTTPCodes.OK && responseArts is ArtSearchResponseDto) {
                val responseArtworks =
                    networkClient.doRequest(ArtSearchRequest.ArtworksRequest(responseArts.data.map { it.id }))
                if (responseArtworks.resultCode == HTTTPCodes.OK && responseArtworks is ArtworkSearchResponseDto) {
                    val data = responseArtworks.data.zip(responseArts.data) { artwork, art ->
                        art.toItem(artwork)
                    }
                    result = ArtSearchResponse(data, responseArts.paginationDto.toPaginationState())
                }
            }
        }

        result.let {
            return if (it != null) {
                Resource.Success(it)
            } else {
                Resource.Error("Unknown error")
            }
        }
    }

}