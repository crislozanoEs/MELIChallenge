package com.crisav2.challengemeli.di

import com.crisav2.challengemeli.home.di.HomeComponent
import com.crisav2.challengemeli.home.di.HomeModule
import com.crisav2.challengemeli.list.di.ProductListComponent
import com.crisav2.challengemeli.list.di.ProductListModule
import com.crisav2.challengemeli.product.di.ProductDetailComponent
import com.crisav2.challengemeli.product.di.ProductDetailModule

interface BaseApplicationComponent {

    fun plus(homeModule: HomeModule): HomeComponent
    fun plus(productListModule: ProductListModule): ProductListComponent
    fun plus(productDetailModule: ProductDetailModule): ProductDetailComponent
}