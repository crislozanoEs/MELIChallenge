package com.crisav2.challengemeli.repository

import com.crisav2.challengemeli.repository.model.ProductNetwork
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductAPI{
    @GET("search/")
    fun getProducts(@Query("ids")productID: String): Single<ProductNetwork>
}