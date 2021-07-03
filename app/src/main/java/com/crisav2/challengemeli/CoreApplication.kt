package com.crisav2.challengemeli

import android.app.Application
import android.content.Context
import com.crisav2.challengemeli.di.BaseApplicationComponent
import com.crisav2.challengemeli.di.component.ApplicationComponent
import com.crisav2.challengemeli.di.component.DaggerApplicationComponent
import com.crisav2.challengemeli.di.module.ApplicationModule

class CoreApplication : Application() {

    companion object{
        fun get(context: Context) = context.applicationContext as CoreApplication

    }

    lateinit var applicationComponent: BaseApplicationComponent

    override fun onCreate() {
        super.onCreate()
        createApplicationComponent()
    }

    private fun createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}