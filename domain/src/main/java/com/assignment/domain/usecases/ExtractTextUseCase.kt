package com.assignment.domain.usecases

import com.assignment.domain.repositories.ExtractWebPageRepository
import com.assignment.domain.schedulers.SingleTransformation
import io.reactivex.Single
import javax.inject.Inject

class ExtractTextUseCase @Inject constructor(
    transformer: SingleTransformation<String>,
    private val fetchWebContentUseCase: FetchWebContentUseCase,
    private val extractWebPageRepository: ExtractWebPageRepository

) : SingleUseCase<Unit, String>(transformer) {

    override fun observable(request: Unit): Single<String> {
        return fetchWebContentUseCase().flatMap {
            extractWebPageRepository.extractTextFromWeb(it)
        }
    }
}