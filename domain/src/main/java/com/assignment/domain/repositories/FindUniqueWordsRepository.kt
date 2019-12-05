package com.assignment.domain.repositories

import io.reactivex.Single

interface FindUniqueWordsRepository {

    fun findUniqueWords(data: String): Single<HashMap<String, Int>>
}