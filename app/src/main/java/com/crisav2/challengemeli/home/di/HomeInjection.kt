package com.crisav2.challengemeli.home.di

import android.content.Context
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.common.MessageManager
import com.crisav2.challengemeli.di.FragmentScope
import com.crisav2.challengemeli.home.Home
import com.crisav2.challengemeli.home.presenter.HomePresenter
import com.crisav2.challengemeli.home.view.HomeFragment
import com.crisav2.core.usecase.Validators
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@FragmentScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent{
    fun inject(fragment: HomeFragment)
}


@Module
class HomeModule(private val view: Home.View){

    @Provides
    @FragmentScope
    fun provideHomePresenter(
        messageManager: IMessageManager,
        validators: Validators
    ): Home.Presenter = HomePresenter(
        view,
        messageManager,
        validators,
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    )
}
