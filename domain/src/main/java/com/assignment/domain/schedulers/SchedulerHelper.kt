package com.assignment.domain.schedulers

object SchedulerHelper {

    fun <T: Any> getTransformer(): AsynchronousTransformer<T> {
        return AsynchronousTransformer()
    }
}