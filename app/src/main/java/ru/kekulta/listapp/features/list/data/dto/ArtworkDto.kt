package ru.kekulta.listapp.features.list.data.dto

import com.google.gson.annotations.SerializedName

data class ArtworkDto(
    @SerializedName("image_id") val image_id: String,
    @SerializedName("date_start") val date_start: Int?,
    @SerializedName("date_end") val date_end: Int?,
    @SerializedName("artist_display") val artist: String?,
    @SerializedName("place_of_origin") val place_of_origin: String?,
)


