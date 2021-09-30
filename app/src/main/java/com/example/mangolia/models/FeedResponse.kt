package com.example.mangolia.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Root(
    @Json(name = "data") val data: Data?
): Parcelable

@Parcelize
data class Data(
    @Json(name = "curation") val curation: Curation?
): Parcelable

@Parcelize
data class Curation(
    @Json(name = "packages") val packages: List<Packages>?,
    @Json(name = "id") val id: String?
): Parcelable

@Parcelize
data class Packages(
    @Json(name = "packageType") val packageType: String?,
    @Json(name = "items") val items: List<Items>?,
    @Json(name = "visibleItemIndex")var visibleItemIndex: Int = -1
): Parcelable

@Parcelize
data class Items(
    @Json(name = "contentType") val contentType: String?,
    @Json(name = "source") val source: Source?,
): Parcelable


@Parcelize
data class Source(
    @Json(name = "id") val id: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "brand") val brand: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "image") val image: Image?
): Parcelable

@Parcelize
data class Image(
    @Json(name = "url") val url: String?,
): Parcelable





