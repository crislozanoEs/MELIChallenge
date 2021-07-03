package com.crisav2.challengemeli.repository

import com.crisav2.challengemeli.repository.model.SearchResultNetwork
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI{
    @GET("search/")
    fun getProducts(@Query("q")keyword: String): Single<SearchResultNetwork>
}