package com.assignment.data.repositories

import org.junit.Test

class FetchUniqueWordsRepositoryImplTest {

    private val fetchUniqueWordsRepositoryImpl = FetchUniqueWordsRepositoryImpl()

    @Test
    fun shouldAssertValue_whenFetchUniqueWordsIsSuccessful() {
        // GIVEN
        val givenText = "I am akshay borgave akshay"
        val expectedResult = hashMapOf<String, Int>()
        expectedResult["I"] = 1
        expectedResult["am"] = 1
        expectedResult["akshay"] = 2
        expectedResult["borgave"] = 1

        // WHEN
        val observer = fetchUniqueWordsRepositoryImpl.findUniqueWords(givenText).test()

        // THEN
        observer.assertValue(expectedResult)
            .assertComplete()
    }
}