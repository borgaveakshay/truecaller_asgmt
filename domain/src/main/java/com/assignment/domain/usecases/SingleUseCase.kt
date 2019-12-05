package com.assignment.domain.usecases

import com.assignment.domain.schedulers.SingleTransformation
import io.reactivex.Single

abstract class SingleUseCase<REQ, RES>(private val singleTransformation: SingleTransformation<RES>) {

    abstract fun observable(request: REQ): Single<RES>

    fun observe(request: REQ): Single<RES> = observable(request).compose(singleTransformation)

}

operator fun <REQ, RES> SingleUseCase<REQ, RES>.invoke(request: REQ) = observable(request)
operator fun <RES> SingleUseCase<Unit, RES>.invoke() = observable(Unit)