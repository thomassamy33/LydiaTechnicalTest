package com.tsamy.lydiatechnicaltest.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

object PaginationConfig {
    const val PAGE_SIZE = 20
}

@JsonClass(generateAdapter = true)
data class InfoDto(
    @Json(name = "seed")
    val seed: String? = null,
    @Json(name = "results")
    val results: Int? = null,
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "version")
    val version: String? = null,
)