package com.assignment.domain.usecases

import com.assignment.domain.repositories.FindUniqueWordsRepository
import com.assignment.domain.schedulers.SingleTransformation
import io.reactivex.Single
import javax.inject.Inject

class FetchUniqueWordsUseCase @Inject constructor(
    transformer: SingleTransformation<HashMap<String, Int>>,
    private val extractTextUseCase: ExtractTextUseCase,
    private val findUniqueWordsRepository: FindUniqueWordsRepository
) : SingleUseCase<Unit, HashMap<String, Int>>(transformer) {

    override fun observable(request: Unit): Single<HashMap<String, Int>> {
        return extractTextUseCase().flatMap {
            findUniqueWordsRepository.findUniqueWords(it)
        }
    }
}