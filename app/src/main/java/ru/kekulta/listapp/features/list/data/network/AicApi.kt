package ru.kekulta.listapp.features.list.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kekulta.listapp.features.list.data.dto.ArtSearchResponseDto
import ru.kekulta.listapp.features.list.data.dto.ArtworkSearchResponseDto


interface AicApi {
    @GET("artworks/search")
    suspend fun searchArt(@Query("q") query: String, @Query("page") page: Int): Response<ArtSearchResponseDto>

    @GET("artworks")
    suspend fun getArtworks(@Query("ids") ids: String): Response<ArtworkSearchResponseDto>
}