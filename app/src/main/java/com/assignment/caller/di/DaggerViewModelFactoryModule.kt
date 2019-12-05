package com.assignment.caller.di

import androidx.lifecycle.ViewModelProvider
import com.assignment.caller.util.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class DaggerViewModelFactoryModule {


    @Binds
    abstract fun bindViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}