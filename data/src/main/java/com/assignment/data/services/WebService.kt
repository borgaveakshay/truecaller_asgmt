package com.assignment.data.services

import io.reactivex.Single
import retrofit2.http.GET

interface WebService {

    @GET("/2018/01/22/life-as-an-android-engineer/")
    fun getWebsiteContent(): Single<String>
}