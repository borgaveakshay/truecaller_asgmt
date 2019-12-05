package com.assignment.domain.usecases

import com.assignment.domain.repositories.ExtractWebPageRepository
import com.assignment.domain.repositories.FetchTenthCharRepository
import com.assignment.domain.repositories.WebFetchRepository
import com.assignment.domain.schedulers.SingleTestTransformer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class FetchTenthCharacterUseCaseTest {

    private val webFetchRepository: WebFetchRepository = Mockito.mock(
        WebFetchRepository::class.java
    )
    private val extractWebPageRepository = Mockito.mock(ExtractWebPageRepository::class.java)
    private val fetchTenthCharRepository = Mockito.mock(FetchTenthCharRepository::class.java)


    @Test
    fun shouldAssertValue_whenFetchCharIsSuccessful() {
        // GIVEN
        givenSuccessfulFetchCall()
        givenSuccessfulExtractCall()
        givenSuccessfulFetchChar()
        val fetchTenthCharacterUseCase = FetchWebContentUseCase(
            SingleTestTransformer()
            , webFetchRepository
        )
        val extractTextUseCase = ExtractTextUseCase(
            SingleTestTransformer(),
            fetchTenthCharacterUseCase,
            extractWebPageRepository
        )

        val useCase = FetchTenthCharacterUseCase(
            SingleTestTransformer(),
            fetchTenthCharRepository,
            extractTextUseCase
        )

        // WHEN
        val observer = useCase.observable(Unit).test()

        // THEN
        observer.assertValue('a')
            .assertComplete()


    }

    @Test
    fun shouldAssertError_whenFetchCharIsUnsuccessful() {
        // GIVEN
        val error = RuntimeException()
        givenSuccessfulFetchCall()
        givenSuccessfulExtractCall()
        givenUnsuccessfulFetchChar(error)
        val fetchTenthCharacterUseCase = FetchWebContentUseCase(
            SingleTestTransformer()
            , webFetchRepository
        )
        val extractTextUseCase = ExtractTextUseCase(
            SingleTestTransformer(),
            fetchTenthCharacterUseCase,
            extractWebPageRepository
        )
        val useCase = FetchTenthCharacterUseCase(
            SingleTestTransformer(),
            fetchTenthCharRepository,
            extractTextUseCase
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

    private fun givenSuccessfulFetchChar() {
        whenever(fetchTenthCharRepository.getTenthCharacter(any())).thenReturn(Single.just('a'))
    }

    private fun givenUnsuccessfulFetchChar(error: RuntimeException) {
        whenever(fetchTenthCharRepository.getTenthCharacter(any())).thenReturn(Single.error(error))
    }

}