package com.crisav2.challengemeli.repository.model

import com.crisav2.core.data.Product
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductNetwork (
    @Json(name = "id")
    val id: String = "",
    @Json(name = "site_id")
    val siteId: String = "",
    @Json(name = "title")
    val titles: String = "",
    @Json(name = "currency_id")
    val currencyId: String = "",
    @Json(name = "price")
    val price: String = "",
    @Json(name = "available_quantity")
    val availableQuantity: Int = 0,
    @Json(name = "sold_quantity")
    val soldQuantity: Int = 0,
    @Json(name = "thumbnail")
    val thumbnail: String = ""
)

fun ProductNetwork.asProduct(): Product{
    return Product(
        id = id,
        siteId = siteId,
        titles = titles,
        currencyId = currencyId,
        price = price,
        availableQuantity = availableQuantity,
        soldQuantity = soldQuantity,
        thumbnail = thumbnail
    )
}