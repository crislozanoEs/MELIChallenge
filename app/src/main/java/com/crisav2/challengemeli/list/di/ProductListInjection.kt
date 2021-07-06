package com.crisav2.challengemeli.list.di

import android.content.Context
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.di.FragmentScope
import com.crisav2.challengemeli.list.ProductList
import com.crisav2.challengemeli.list.presenter.ProductListPresenter
import com.crisav2.challengemeli.list.view.ProductListFragment
import com.crisav2.challengemeli.repository.SearchAPI
import com.crisav2.core.usecase.Transformation
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [ProductListModule::class])
interface ProductListComponent{
    fun inject(fragment: ProductListFragment)
}

@Module
class ProductListModule(private val view: ProductList.View){

    @Provides
    @FragmentScope
    fun providePresenter(
        messageManager: IMessageManager,
        searchAPI: SearchAPI,
        transformation: Transformation
    ): ProductList.Presenter = ProductListPresenter(view, messageManager, searchAPI, transformation)
}