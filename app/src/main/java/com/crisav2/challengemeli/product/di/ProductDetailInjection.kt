package com.crisav2.challengemeli.product.di

import android.content.Context
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.di.FragmentScope
import com.crisav2.challengemeli.list.view.ProductListFragment
import com.crisav2.challengemeli.product.ProductDetail
import com.crisav2.challengemeli.product.presenter.ProductDetailPresenter
import com.crisav2.challengemeli.product.view.ProductDetailFragment
import com.crisav2.challengemeli.repository.ProductAPI
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ProductDetailModule::class])
interface ProductDetailComponent{
    fun inject(fragment: ProductDetailFragment)
}

@Module
class ProductDetailModule(private val view: ProductDetail.View){

    @Provides
    @FragmentScope
    fun providePresenter(
        context: Context,
        messageManager: IMessageManager,
        productAPI: ProductAPI
    ): ProductDetail.Presenter = ProductDetailPresenter(view, messageManager, productAPI)
}