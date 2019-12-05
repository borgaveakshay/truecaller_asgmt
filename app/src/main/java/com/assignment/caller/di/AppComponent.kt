package com.assignment.caller.di

import android.app.Application
import com.assignment.caller.App
import com.assignment.data.di.DataModule
import com.assignment.data.di.NetworkModule
import com.assignment.domain.di.TransformerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        DataModule::class,
        TransformerModule::class,
        ActivityBuilderModule::class,
        DaggerViewModelFactoryModule::class,
        ViewModelsModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}