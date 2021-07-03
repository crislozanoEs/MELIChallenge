package com.crisav2.challengemeli.di.component

import com.crisav2.challengemeli.di.BaseApplicationComponent
import com.crisav2.challengemeli.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent: BaseApplicationComponent{

}