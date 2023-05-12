package ru.kekulta.listapp.features.list.data.dto

sealed class ArtSearchRequest {
    class QueryRequest(val query: String, val page: Int = 1) : ArtSearchRequest()
    class ArtworksRequest(val ids: List<Int>) : ArtSearchRequest()
}
