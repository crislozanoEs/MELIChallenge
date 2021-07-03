package com.crisav2.core.data


data class SearchResult(
    val siteId: String = "",
    val query: String = "",
    val paging: Paging? = null,
    val results: List<Product>? = null
)