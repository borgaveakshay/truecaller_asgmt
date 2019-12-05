package com.assignment.caller.di

import androidx.lifecycle.ViewModel
import com.assignment.caller.util.ViewModelKey
import com.assignment.caller.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}