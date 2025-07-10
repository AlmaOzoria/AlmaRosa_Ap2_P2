package edu.ucne.almarosa_ap2_p2.remote.dto

import com.squareup.moshi.Json

data class ContribuidorDto(
    @Json(name = "login") val login: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "contributions") val contributions: Int
    )
