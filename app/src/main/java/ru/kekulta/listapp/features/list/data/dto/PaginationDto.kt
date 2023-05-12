package ru.kekulta.listapp.features.list.data.dto

import com.google.gson.annotations.SerializedName
import ru.kekulta.listapp.features.list.domain.models.PaginationState

data class PaginationDto(
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("current_page") val currentPage: Int,
)

fun PaginationDto.toPaginationState(): PaginationState = PaginationState(currentPage, totalPages)