package com.assignment.data.repositories

import com.assignment.domain.repositories.ExtractWebPageRepository
import io.reactivex.Single
import org.jsoup.Jsoup
import javax.inject.Inject

class ExtractTextRepositoryImpl @Inject constructor() : ExtractWebPageRepository {

    override fun extractTextFromWeb(pageContent: String): Single<String> {
        val document = Jsoup.parse(pageContent)
        val response = document.text()
        return Single.just(response)
    }
}