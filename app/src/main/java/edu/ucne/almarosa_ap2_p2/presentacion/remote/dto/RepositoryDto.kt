package edu.ucne.almarosa_ap2_p2.presentacion.remote.dto

import com.squareup.moshi.Json

data class RepositoryDto(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String?,
    @Json(name = "html_url") val htmlUrl: String
)