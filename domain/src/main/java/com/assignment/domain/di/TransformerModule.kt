package com.assignment.domain.di

import com.assignment.domain.schedulers.AsynchronousTransformer
import com.assignment.domain.schedulers.SingleTransformation
import dagger.Module
import dagger.Provides

@Module
class TransformerModule {

    @Provides
    fun getStringTransformer(): SingleTransformation<String> = AsynchronousTransformer()

    @Provides
    fun getCharArrayTransformer(): SingleTransformation<Array<Char>> = AsynchronousTransformer()

    @Provides
    fun getCharTransformer(): SingleTransformation<Char> = AsynchronousTransformer()

    @Provides
    fun getHashMapTransformer(): SingleTransformation<HashMap<String, Int>> =
        AsynchronousTransformer()
}