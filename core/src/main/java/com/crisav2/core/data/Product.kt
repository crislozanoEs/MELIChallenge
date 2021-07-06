package com.crisav2.core.data

data class Product (
    val id: String = "",
    val siteId: String = "",
    val titles: String = "",
    val seller: Seller? = null,
    val currencyId: String = "",
    val price: String = "",
    val availableQuantity: Int = 0,
    val soldQuantity: Int = 0,
    val thumbnail: String = "",
    val secureThumbnail: String = ""
)