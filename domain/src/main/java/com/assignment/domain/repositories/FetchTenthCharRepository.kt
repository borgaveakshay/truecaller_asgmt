package com.assignment.domain.repositories

import io.reactivex.Single

interface FetchTenthCharRepository {

    fun getTenthCharacter(data: String): Single<Char>

    fun getEveryTenthCharacter(data: String): Single<Array<Char>>
}