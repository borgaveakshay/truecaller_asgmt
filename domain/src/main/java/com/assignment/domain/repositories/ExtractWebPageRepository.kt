package com.assignment.domain.repositories

import io.reactivex.Single

interface ExtractWebPageRepository {

    fun extractTextFromWeb(pageContent: String): Single<String>
}