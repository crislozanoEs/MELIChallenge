package com.crisav2.challengemeli.home.di

import android.content.Context
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.common.MessageManager
import com.crisav2.challengemeli.di.FragmentScope
import com.crisav2.challengemeli.home.Home
import com.crisav2.challengemeli.home.presenter.HomePresenter
import com.crisav2.challengemeli.home.view.HomeFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


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
        context: Context,
        messageManager: IMessageManager
    ): Home.Presenter = HomePresenter(context, view, messageManager)
}
