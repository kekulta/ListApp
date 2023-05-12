package ru.kekulta.listapp.features.list.data.dto

import com.google.gson.annotations.SerializedName


data class ArtSearchResponseDto(
    @SerializedName("data") val data: List<ArtDto>,
    @SerializedName("pagination") val paginationDto: PaginationDto,
) : Response()