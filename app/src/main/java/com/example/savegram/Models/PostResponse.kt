package com.example.savegram.Models

import com.google.gson.annotations.SerializedName

data class PostResponse (
    @SerializedName("num_results") val numResults: Int,
    @SerializedName("more_available") val moreAvailable: Boolean,
    @SerializedName("items") val items: List<InstagramItem>
)

data class InstagramItem(
    @SerializedName("carousel_media") val carouselMedia: List<CarouselMedia>
)

data class CarouselMedia(
    @SerializedName("image_versions2") val imageVersions: ImageVersions
)

data class ImageVersions(
    @SerializedName("candidates") val candidates: List<ImageCandidate>
)

data class ImageCandidate(
    @SerializedName("url") val url: String
)