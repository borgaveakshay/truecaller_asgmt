package com.assignment.data.di

import com.assignment.data.repositories.ExtractTextRepositoryImpl
import com.assignment.data.repositories.FetchTenthCharRepositoryImpl
import com.assignment.data.repositories.FetchUniqueWordsRepositoryImpl
import com.assignment.data.repositories.WebContentRepositoryImpl
import com.assignment.domain.repositories.ExtractWebPageRepository
import com.assignment.domain.repositories.FetchTenthCharRepository
import com.assignment.domain.repositories.FindUniqueWordsRepository
import com.assignment.domain.repositories.WebFetchRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun webRepository(webContentRepositoryImpl: WebContentRepositoryImpl): WebFetchRepository

    @Binds
    abstract fun extractTextRepository(extractTextRepositoryImpl: ExtractTextRepositoryImpl): ExtractWebPageRepository

    @Binds
    abstract fun findTenthCharacterRepository(fetchTenthCharRepositoryImpl: FetchTenthCharRepositoryImpl): FetchTenthCharRepository

    @Binds
    abstract fun findUniqueWordsRepository(findUniqueWordsRepositoryImpl: FetchUniqueWordsRepositoryImpl): FindUniqueWordsRepository

}