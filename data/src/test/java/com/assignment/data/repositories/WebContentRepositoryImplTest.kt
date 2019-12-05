package com.assignment.data.repositories

import com.assignment.data.repositories.WebContentRepositoryImpl
import com.assignment.data.services.WebService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test


class WebContentRepositoryImplTest {

    private val webService: WebService = mock()


    @Test
    fun shouldAssertValue_whenFetchSuccessful() {
        // GIVEN
        givenSuccessfulCall()
        val repository = WebContentRepositoryImpl(webService)

        // WHEN
        val observer = repository.getWebsiteContent().test()

        // THEN
        observer.assertValue("12345")
            .assertComplete()
    }

    @Test
    fun shouldAssertError_whenFetchUnsuccessful() {
        // GIVEN
        val error = RuntimeException()
        givenUnsuccessfulCall(error)
        val repository = WebContentRepositoryImpl(webService)

        // WHEN
        val observer = repository.getWebsiteContent().test()

        // THEN
        observer.assertError(error)
            .assertNotComplete()
    }


    private fun givenSuccessfulCall() {
        whenever(webService.getWebsiteContent()).thenReturn(Single.just("12345"))
    }

    private fun givenUnsuccessfulCall(error: RuntimeException) {
        whenever(webService.getWebsiteContent()).thenReturn(Single.error(error))
    }

}