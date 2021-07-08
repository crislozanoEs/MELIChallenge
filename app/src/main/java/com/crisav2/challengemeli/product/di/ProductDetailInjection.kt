package com.crisav2.challengemeli.product.di

import android.content.Context
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.di.FragmentScope
import com.crisav2.challengemeli.list.view.ProductListFragment
import com.crisav2.challengemeli.product.ProductDetail
import com.crisav2.challengemeli.product.presenter.ProductDetailPresenter
import com.crisav2.challengemeli.product.view.ProductDetailFragment
import com.crisav2.challengemeli.repository.ProductAPI
import com.crisav2.core.usecase.Transformation
import com.crisav2.core.usecase.Validators
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
        messageManager: IMessageManager,
        productAPI: ProductAPI,
        validators: Validators,
        transformation: Transformation
    ): ProductDetail.Presenter = ProductDetailPresenter(
        view,
        messageManager,
        productAPI,
        validators,
        transformation,
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    )
}