package edu.ucne.almarosa_ap2_p2.remote.dto

import com.squareup.moshi.Json

data class OwnerDto(
    val login: String
)

data class RepositoryDto(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String?,
    @Json(name = "html_url") val htmlUrl: String,
    val owner: OwnerDto
)
