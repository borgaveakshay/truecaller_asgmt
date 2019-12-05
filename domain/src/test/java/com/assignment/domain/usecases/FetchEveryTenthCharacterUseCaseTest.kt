package com.assignment.domain.usecases

import com.assignment.domain.repositories.ExtractWebPageRepository
import com.assignment.domain.repositories.FetchTenthCharRepository
import com.assignment.domain.repositories.WebFetchRepository
import com.assignment.domain.schedulers.SingleTestTransformer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class FetchEveryTenthCharacterUseCaseTest {

    private val fetchTenthCharRepository = mock<FetchTenthCharRepository>()
    private val extractWebPageRepository = mock<ExtractWebPageRepository>()
    private val webFetchRepository = mock<WebFetchRepository>()

    @Test
    fun shouldAssertValue_whenFetchEveyTenthCharacterIsSuccessful() {
        // GIVEN
        val expectedArray = arrayOf('a', 'b', 'c')
        givenSuccessfulFetchContent()
        givenSuccessfulExtractContent()
        givenSuccessfulResultArray(expectedArray)
        val webContentUseCase = FetchWebContentUseCase(
            SingleTestTransformer(),
            webFetchRepository
        )
        val extractTextUseCase = ExtractTextUseCase(
            SingleTestTransformer(),
            webContentUseCase,
            extractWebPageRepository
        )
        val useCase = FetchEveryTenthCharacterUseCase(
            SingleTestTransformer(),
            extractTextUseCase,
            fetchTenthCharRepository
        )

        // WHEN
        val observer = useCase.observable(Unit).test()

        // THEN
        observer.assertValue(expectedArray)
            .assertComplete()

    }

    @Test
    fun shouldAssertError_whenFetchEveyTenthCharacterIsUnsuccessful() {
        // GIVEN
        val expectedError = RuntimeException()
        givenSuccessfulFetchContent()
        givenSuccessfulExtractContent()
        givenUnsuccessfulResultArray(expectedError)
        val webContentUseCase = FetchWebContentUseCase(
            SingleTestTransformer(),
            webFetchRepository
        )
        val extractTextUseCase = ExtractTextUseCase(
            SingleTestTransformer(),
            webContentUseCase,
            extractWebPageRepository
        )
        val useCase = FetchEveryTenthCharacterUseCase(
            SingleTestTransformer(),
            extractTextUseCase,
            fetchTenthCharRepository
        )

        // WHEN
        val observer = useCase.observable(Unit).test()

        // THEN
        observer.assertError(expectedError)
            .assertNotComplete()

    }

    private fun givenSuccessfulExtractContent() {
        whenever(extractWebPageRepository.extractTextFromWeb(any()))
            .thenReturn(Single.just("12345"))
    }

    private fun givenSuccessfulFetchContent() {
        whenever(webFetchRepository.getWebsiteContent())
            .thenReturn(Single.just("12345"))
    }

    private fun givenSuccessfulResultArray(result: Array<Char>) {
        whenever(fetchTenthCharRepository.getEveryTenthCharacter(any()))
            .thenReturn(Single.just(result))
    }

    private fun givenUnsuccessfulResultArray(error: RuntimeException) {
        whenever(fetchTenthCharRepository.getEveryTenthCharacter(any()))
            .thenReturn(Single.error(error))
    }
}