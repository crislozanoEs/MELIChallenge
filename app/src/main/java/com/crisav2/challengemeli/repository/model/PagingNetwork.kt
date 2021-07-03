package com.crisav2.challengemeli.repository.model

import com.crisav2.core.data.Paging
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PagingNetwork(
    @Json(name ="total")
    val total: Int = 0,
    @Json(name ="primary_results")
    val primaryResults: Int = 0,
    @Json(name ="offset")
    val offset: Int = 0,
    @Json(name ="limit")
    val limit: Int = 0
)

fun PagingNetwork.asPaging(): Paging{
    return Paging(
        total = total,
        primaryResults = primaryResults,
        offset = offset,
        limit = limit
    )
}
