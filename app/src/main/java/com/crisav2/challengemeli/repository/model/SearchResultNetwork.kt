package com.crisav2.challengemeli.repository.model

import android.net.Network
import com.crisav2.core.data.Product
import com.crisav2.core.data.SearchResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultNetwork(
    @Json(name ="site_id")
    val siteId: String = "",
    @Json(name ="query")
    val query: String = "",
    @Json(name ="paging")
    val pagingNetwork: PagingNetwork? = null,
    @Json(name ="results")
    val results: List<ProductNetwork>? = null
)

fun SearchResultNetwork.asSearchResult(): SearchResult{
    val products = mutableListOf<Product>()
    results?.forEach {
        products.add(it.asProduct())
    }
    return SearchResult(
        siteId = siteId,
        query = query,
        paging = pagingNetwork?.asPaging(),
        results = products
    )
}