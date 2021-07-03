package com.crisav2.challengemeli.di.module

import android.app.Application
import android.content.Context
import com.crisav2.challengemeli.common.Constants
import com.crisav2.challengemeli.common.IMessageManager
import com.crisav2.challengemeli.common.MessageManager
import com.crisav2.challengemeli.repository.ProductAPI
import com.crisav2.challengemeli.repository.SearchAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun context(): Context = application

    @Provides
    fun provideMessageManager(): IMessageManager = MessageManager(context())

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun createRetrofit() = Retrofit.Builder()
        .baseUrl(Constants.baseURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideSearchApi(): SearchAPI = createRetrofit().create(SearchAPI::class.java)

    @Provides
    @Singleton
    fun provideProductApi(): ProductAPI = createRetrofit().create(ProductAPI::class.java)
}