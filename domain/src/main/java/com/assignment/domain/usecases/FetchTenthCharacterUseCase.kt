package com.assignment.domain.usecases

import com.assignment.domain.repositories.FetchTenthCharRepository
import com.assignment.domain.schedulers.SingleTransformation
import io.reactivex.Single
import javax.inject.Inject

class FetchTenthCharacterUseCase @Inject constructor(
    transformer: SingleTransformation<Char>,
    private val fetchTenthCharRepository: FetchTenthCharRepository,
    private val extractTextUseCase: ExtractTextUseCase
) : SingleUseCase<Unit, Char>(transformer) {

    override fun observable(request: Unit): Single<Char> {
        return extractTextUseCase().flatMap {
            fetchTenthCharRepository.getTenthCharacter(it)
        }
    }
}