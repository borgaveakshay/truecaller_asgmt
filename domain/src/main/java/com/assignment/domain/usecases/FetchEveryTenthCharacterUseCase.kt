package com.assignment.domain.usecases

import com.assignment.domain.repositories.FetchTenthCharRepository
import com.assignment.domain.schedulers.SingleTransformation
import io.reactivex.Single
import javax.inject.Inject

class FetchEveryTenthCharacterUseCase @Inject constructor(
    transformer: SingleTransformation<Array<Char>>,
    private val extractTextUseCase: ExtractTextUseCase,
    private val fetchTenthCharRepository: FetchTenthCharRepository
) : SingleUseCase<Unit, Array<Char>>(transformer) {

    override fun observable(request: Unit): Single<Array<Char>> {
        return extractTextUseCase().flatMap {
            fetchTenthCharRepository.getEveryTenthCharacter(it)
        }
    }
}