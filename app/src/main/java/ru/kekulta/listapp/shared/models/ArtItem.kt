package ru.kekulta.listapp.shared.models

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class ArtItem(
    val id: Int,
    val title: String,
    val imageId: String,
    val dateStart: Int?,
    val dateEnd: Int?,
    val artist: String?,
    val placeOfOrigin: String?,
) : Parcelable

val ArtParametersType = object : NavType<ArtItem>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: ArtItem) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): ArtItem {
        return bundle.getParcelable<ArtItem>(key) as ArtItem
    }

    override fun parseValue(value: String): ArtItem {
        return Json.decodeFromString<ArtItem>(value)
    }
}


