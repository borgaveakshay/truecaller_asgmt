package com.assignment.data.repositories

import com.assignment.data.services.WebService
import com.assignment.domain.repositories.WebFetchRepository
import io.reactivex.Single
import javax.inject.Inject

class WebContentRepositoryImpl @Inject constructor(
    private val webService: WebService
) : WebFetchRepository {

    override fun getWebsiteContent(): Single<String> = webService.getWebsiteContent()

}