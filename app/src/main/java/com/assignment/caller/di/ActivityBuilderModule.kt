package com.assignment.caller.di

import com.assignment.caller.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesToMainActivity(): MainActivity

}