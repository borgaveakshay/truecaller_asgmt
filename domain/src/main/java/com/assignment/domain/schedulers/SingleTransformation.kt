package com.assignment.domain.schedulers

import io.reactivex.SingleTransformer

abstract class SingleTransformation<T> : SingleTransformer<T, T>