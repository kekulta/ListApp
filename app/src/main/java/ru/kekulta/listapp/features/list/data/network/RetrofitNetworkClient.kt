package ru.kekulta.listapp.features.list.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kekulta.listapp.features.list.data.NetworkClient
import ru.kekulta.listapp.features.list.data.dto.ArtSearchRequest
import ru.kekulta.listapp.features.list.data.dto.Response
import ru.kekulta.listapp.shared.utils.HTTTPCodes

class RetrofitNetworkClient : NetworkClient {
    private val aicBaseUrl = "https://api.artic.edu/api/v1/"

    private val retrofit =
        Retrofit.Builder().baseUrl(aicBaseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()

    private val aicService = retrofit.create(AicApi::class.java)
    override suspend fun doRequest(dto: Any): Response {
        return if (dto is ArtSearchRequest) {
            when (dto) {
                is ArtSearchRequest.QueryRequest -> {
                    val resp = aicService.searchArt(dto.query, dto.page)
                    val body = resp.body() ?: Response()
                    body.apply { resultCode = resp.code() }
                }

                is ArtSearchRequest.ArtworksRequest -> {
                    val resp = aicService.getArtworks(dto.ids.joinToString(separator = ","))
                    val body = resp.body() ?: Response()
                    body.apply { resultCode = resp.code() }
                }
            }

        } else {
            Response().apply { resultCode = HTTTPCodes.BAD_REQUEST }
        }
    }
}