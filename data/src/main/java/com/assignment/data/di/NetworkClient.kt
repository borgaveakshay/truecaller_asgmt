package com.assignment.data.di

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class NetworkClient @Inject constructor(
    private val retrofit: Retrofit
) {

    fun <T : Any> create(service: KClass<T>): T {

        return retrofit.create(service.java)
    }
}