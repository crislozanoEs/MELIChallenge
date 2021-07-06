package com.crisav2.challengemeli.repository

import com.crisav2.challengemeli.repository.model.ProductNetwork
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductAPI{
    @GET("items/{item_id}")
    fun getProducts(@Path(value = "item_id", encoded = true) productID: String): Single<ProductNetwork>
}