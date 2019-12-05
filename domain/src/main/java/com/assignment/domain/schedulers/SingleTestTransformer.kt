package com.assignment.domain.schedulers

import io.reactivex.Single
import io.reactivex.SingleSource

class SingleTestTransformer<T> : SingleTransformation<T>(){
    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
    }
}