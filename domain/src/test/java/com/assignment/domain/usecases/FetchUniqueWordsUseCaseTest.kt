package com.assignment.domain.usecases

import com.assignment.domain.repositories.ExtractWebPageRepository
import com.assignment.domain.repositories.FindUniqueWordsRepository
import com.assignment.domain.repositories.WebFetchRepository
import com.assignment.domain.schedulers.SingleTestTransformer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class FetchUniqueWordsUseCaseTest {

    private val webFetchRepository: WebFetchRepository = Mockito.mock(
        WebFetchRepository::class.java
    )
    private val extractWebPageRepository = Mockito.mock(ExtractWebPageRepository::class.java)
    private val findUniqueWordsRepository = Mockito.mock(FindUniqueWordsRepository::class.java)

    @Test
    fun shouldAssertValue_whenFetchUniqueWordsIsSuccessful() {
        // GIVEN
        val expectedResult = hashMapOf<String, Int>()
        expectedResult["a"] = 1
        expectedResult["b"] = 2
        givenSuccessfulFetchCall()
        givenSuccessfulExtractCall()
        givenSuccessfulFetchWords(expectedResult)
        val fetchWebContentUseCase = FetchWebContentUseCase(
            SingleTestTransformer()
            , webFetchRepository
        )
        val extractTextUseCase = ExtractTextUseCase(
            SingleTestTransformer(),
            fetchWebContentUseCase,
            extractWebPageRepository
        )
        val useCase = FetchUniqueWordsUseCase(
            SingleTestTransformer(),
            extractTextUseCase,
            findUniqueWordsRepository
        )

        // WHEN
        val observer = useCase.observable(Unit).test()

        // THEN
        observer.assertValue(expectedResult)
            .assertComplete()
    }

    @Test
    fun shouldPropagateError_whenFetchUniqueWordsIsUnsuccessful() {
        // GIVEN
        val error = RuntimeException()
        givenSuccessfulFetchCall()
        givenSuccessfulExtractCall()
        givenUnsuccessfulFetchWords(error)
        val fetchWebContentUseCase = FetchWebContentUseCase(
            SingleTestTransformer()
            , webFetchRepository
        )
        val extractTextUseCase = ExtractTextUseCase(
            SingleTestTransformer(),
            fetchWebContentUseCase,
            extractWebPageRepository
        )
        val useCase = FetchUniqueWordsUseCase(
            SingleTestTransformer(),
            extractTextUseCase,
            findUniqueWordsRepository
        )

        // WHEN
        val observer = useCase.observable(Unit).test()

        // THEN
        observer.assertError(error)
            .assertNotComplete()
    }


    private fun givenSuccessfulExtractCall() {
        whenever(extractWebPageRepository.extractTextFromWeb(any())).thenReturn(Single.just("12345"))
    }

    private fun givenSuccessfulFetchCall() {
        whenever(webFetchRepository.getWebsiteContent()).thenReturn(Single.just("12345"))
    }

    private fun givenSuccessfulFetchWords(expectedResult: HashMap<String, Int>) {
        whenever(findUniqueWordsRepository.findUniqueWords(any())).thenReturn(
            Single.just(
                expectedResult
            )
        )
    }

    private fun givenUnsuccessfulFetchWords(error: RuntimeException) {
        whenever(findUniqueWordsRepository.findUniqueWords(any())).thenReturn(
            Single.error(
                error
            )
        )
    }


}