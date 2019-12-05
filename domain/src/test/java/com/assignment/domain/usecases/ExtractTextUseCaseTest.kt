package com.assignment.domain.usecases

import com.assignment.domain.repositories.ExtractWebPageRepository
import com.assignment.domain.repositories.WebFetchRepository
import com.assignment.domain.schedulers.SingleTestTransformer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class ExtractTextUseCaseTest {
    private val webFetchRepository: WebFetchRepository = Mockito.mock(
        WebFetchRepository::class.java
    )
    private val extractWebPageRepository = Mockito.mock(ExtractWebPageRepository::class.java)
    
    @Test
    fun shouldAssertValue_whenExtractCallIsSuccessful() {
        // GIVEN
        givenSuccessfulFetchCall()
        givenSuccessfulExtractCall()
        val fetchTenthCharacterUseCase = FetchWebContentUseCase(
            SingleTestTransformer()
            , webFetchRepository
        )
        val useCase = ExtractTextUseCase(
            SingleTestTransformer(),
            fetchTenthCharacterUseCase,
            extractWebPageRepository
        )

        // WHEN
        val observer = useCase.observable(Unit).test()

        // THEN
        observer.assertValue("12345")
            .assertComplete()


    }

    @Test
    fun shouldAssertError_whenExtractCallIsUnsuccessful() {
        // GIVEN
        val error = RuntimeException()
        givenSuccessfulFetchCall()
        givenUnsuccessfulExtractCall(error)
        val fetchTenthCharacterUseCase = FetchWebContentUseCase(
            SingleTestTransformer()
            , webFetchRepository
        )
        val useCase = ExtractTextUseCase(
            SingleTestTransformer(),
            fetchTenthCharacterUseCase,
            extractWebPageRepository
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

    private fun givenUnsuccessfulExtractCall(error: RuntimeException) {
        whenever(extractWebPageRepository.extractTextFromWeb(any())).thenReturn(Single.error(error))
    }

    private fun givenSuccessfulFetchCall() {
        whenever(webFetchRepository.getWebsiteContent()).thenReturn(Single.just("12345"))
    }
}