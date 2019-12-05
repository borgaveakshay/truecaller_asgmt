package com.assignment.caller.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.domain.usecases.FetchEveryTenthCharacterUseCase
import com.assignment.domain.usecases.FetchTenthCharacterUseCase
import com.assignment.domain.usecases.FetchUniqueWordsUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fetchTenthCharacterUseCase: FetchTenthCharacterUseCase,
    private val fetchEveryTenthCharacterUseCase: FetchEveryTenthCharacterUseCase,
    private val fetchUniqueWordsUseCase: FetchUniqueWordsUseCase

) : BaseViewModel() {


    fun getTenthCharacter(): LiveData<Char> {
        val liveData = MutableLiveData<Char>()

        fetchTenthCharacterUseCase.observable(Unit)
            .subscribe(
                {
                    liveData.postValue(it)
                },
                {
                    liveData.postValue(null)
                }
            ).autoDispose()

        return liveData
    }


}