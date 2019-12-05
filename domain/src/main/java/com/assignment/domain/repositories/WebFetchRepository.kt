package com.assignment.domain.repositories

import io.reactivex.Single

interface WebFetchRepository {

    fun getWebsiteContent(): Single<String>

}