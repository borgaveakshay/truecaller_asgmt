package com.assignment.data.repositories

import com.assignment.domain.repositories.FetchTenthCharRepository
import io.reactivex.Single
import javax.inject.Inject

class FetchTenthCharRepositoryImpl @Inject constructor() : FetchTenthCharRepository {

    override fun getTenthCharacter(data: String): Single<Char> {
        val trimmedText = data.trim()
        return if (trimmedText.length >= 9) Single.just(data[9]) else Single.error(
            Exception(
                "data is less then 10 chars"
            )
        )
    }

    override fun getEveryTenthCharacter(data: String): Single<Array<Char>> {
        var charCounter = 9
        val array = mutableListOf<Char>()
        val charArray = data.toCharArray()

        while (charArray.size > charCounter) {
            array.add(charArray[charCounter])
            charCounter += 10
        }

        return Single.just(array.toTypedArray())
    }
}