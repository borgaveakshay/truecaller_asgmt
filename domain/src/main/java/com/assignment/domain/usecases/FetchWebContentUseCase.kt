package com.assignment.domain.usecases

import com.assignment.domain.repositories.WebFetchRepository
import com.assignment.domain.schedulers.SingleTransformation
import io.reactivex.Single
import javax.inject.Inject

class FetchWebContentUseCase @Inject constructor(
    transformation: SingleTransformation<String>,
    private val webFetchRepository: WebFetchRepository
) : SingleUseCase<Unit, String>(transformation) {

    override fun observable(request: Unit): Single<String> = webFetchRepository.getWebsiteContent()
}