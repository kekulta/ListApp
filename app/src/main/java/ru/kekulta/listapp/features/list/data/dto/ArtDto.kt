package ru.kekulta.listapp.features.list.data.dto

import com.google.gson.annotations.SerializedName
import ru.kekulta.listapp.shared.models.ArtItem

data class ArtDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
)

fun ArtDto.toItem(artworkDto: ArtworkDto): ArtItem = ArtItem(
    id,
    title,
    artworkDto.image_id,
    artworkDto.date_start,
    artworkDto.date_end,
    artworkDto.artist,
    artworkDto.place_of_origin
)