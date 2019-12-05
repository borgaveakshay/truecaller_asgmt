package com.assignment.data.repositories

import com.assignment.data.repositories.ExtractTextRepositoryImpl
import org.junit.Test


class ExtractTextRepositoryImplTest {

    private val extractTextRepository = ExtractTextRepositoryImpl()
    private val htmlText =
        "<p>Have you ever wondered how it is to be an Android Developer that works on an app with over 250 Million users? " +
                "We had the opportunity to borrow, Mike, for a conversion about his job as an Android Developer, and what kind of mindset one needs to possess to get to where he is today.</p>"
    private val expectedText =
        "Have you ever wondered how it is to be an Android Developer that works on an app with over 250 Million users? " +
                "We had the opportunity to borrow, Mike, for a conversion about his job as an Android Developer, and what kind of mindset one needs to possess to get to where he is today."


    @Test
    fun shouldAssertValue_whenExtractTextSuccessful() {
        // WHEN
        val observer = extractTextRepository.extractTextFromWeb(htmlText).test()

        // THEN
        observer.assertValue(expectedText)
            .assertComplete()

    }


}