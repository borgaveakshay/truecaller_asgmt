package com.assignment.data.repositories

import com.assignment.domain.repositories.FindUniqueWordsRepository
import io.reactivex.Single
import javax.inject.Inject

class FetchUniqueWordsRepositoryImpl @Inject constructor() : FindUniqueWordsRepository {

    override fun findUniqueWords(data: String): Single<HashMap<String, Int>> {
        val map = hashMapOf<String, Int>()
        data.split(" ").map { key ->
            val count = map.getOrElse(key, { 0 })
            map[key] = count + 1
        }
        return Single.just(map)
    }
}