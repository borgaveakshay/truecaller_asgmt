package com.assignment.data.repositories

import org.junit.Test

class FetchTenthCharRepositoryImplTest {

    private val fetchTenthCharRepositoryImpl = FetchTenthCharRepositoryImpl()

    @Test
    fun shouldAssertValue_whenFetchTenthCharIsSuccessful() {
        // GIVEN
        val givenText = "I am akshay borgave"
        val expectedChar = 'a'

        // WHEN
        val observer = fetchTenthCharRepositoryImpl.getTenthCharacter(givenText).test()

        // THEN
        observer.assertValue(expectedChar)
            .assertComplete()

    }

    @Test
    fun shouldAssertError_whenFetchTenthCharIsUnsuccessful() {
        // GIVEN
        val givenErrorString = "I am Aki"
        val expectedErrorMessage = "data is less then 10 chars"

        // WHEN
        val observer = fetchTenthCharRepositoryImpl.getTenthCharacter(givenErrorString).test()

        // THEN
        observer.assertErrorMessage(expectedErrorMessage)
            .assertNotComplete()

    }

    @Test
    fun shouldAssertValue_whenFetchEveryTenthCharIsSuccessful() {
        // GIVEN
        val givenText = "I am akshay borgave I am akshay borgave"
        val expectedChar = arrayOf('a', ' ', 'a')

        // WHEN
        val observer = fetchTenthCharRepositoryImpl.getEveryTenthCharacter(givenText).test()

        // THEN
        observer.assertValue { array ->
            array[0] == expectedChar[0]
            array[1] == expectedChar[1]
            array[2] == expectedChar[2]

        }.assertComplete()

    }
}