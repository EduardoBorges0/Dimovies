package com.app.cinedimen.data.model

import com.google.gson.annotations.SerializedName

data class MovieReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<MovieReviewModel>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieReviewModel(
    val author: String,
    @SerializedName("author_details") val authorDetails: AuthorDetails,
    val content: String,
    @SerializedName("created_at") val createdAt: String,
    val id: String,
    @SerializedName("updated_at") val updatedAt: String,
    val url: String
)

data class AuthorDetails(
    val name: String?,
    val username: String,
    @SerializedName("avatar_path") val avatarPath: String?,
    val rating: Int?
)
