package com.assignment.caller.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * [BaseViewModel] for handling disposing of observables behind the scenes.
 */
open class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    /**
     * Extension function on composite object to clean when view model is cleared.
     */
    fun Disposable.autoDispose() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}