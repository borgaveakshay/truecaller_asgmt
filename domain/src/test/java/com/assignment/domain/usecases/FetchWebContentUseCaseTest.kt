package com.assignment.domain.usecases

import com.assignment.domain.repositories.WebFetchRepository
import com.assignment.domain.schedulers.SingleTestTransformer
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class FetchWebContentUseCaseTest {

    private val webFetchRepository: WebFetchRepository = Mockito.mock(
        WebFetchRepository::class.java
    )

    @Test
    fun shouldAssertValue_whenFetchCallIsSuccessful() {
        // GIVEN
        givenSuccessfulFetchCall()
        val useCase = FetchWebContentUseCase(
            SingleTestTransformer(),
            webFetchRepository
        )
        // WHEN
        val observer = useCase.observable(Unit).test()

        // THEN
        observer.assertNoErrors()
            .assertValue("12345")

    }

    @Test
    fun shouldAssertError_whenFetchCallIsUnsuccessful() {
        // GIVEN
        val error = RuntimeException()
        givenUnsuccessfulFetchCall(error)
        val useCase = FetchWebContentUseCase(
            SingleTestTransformer(),
            webFetchRepository
        )
        // WHEN
        val observer = useCase.observable(Unit).test()

        // THEN
        observer.assertError(error)
            .assertNotComplete()

    }


    private fun givenSuccessfulFetchCall() {
        whenever(webFetchRepository.getWebsiteContent()).thenReturn(Single.just("12345"))
    }

    private fun givenUnsuccessfulFetchCall(error: RuntimeException) {
        whenever(webFetchRepository.getWebsiteContent()).thenReturn(Single.error(error))
    }
}