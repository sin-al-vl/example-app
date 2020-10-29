package com.example.core.mvi.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.mvi.Intent
import com.example.core.mvi.view.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<S : State, I : Intent, R : Result>(
    private val reducers: List<Reducer<R, S>>
) : ViewModel() {

    private val _state = MutableLiveData<S>()

    val state: LiveData<S>
        get() = _state

    val resultChannel = Channel<R>()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            for (result in resultChannel) {
                _state.value = reduceResult(result, state.value)
            }
        }
    }

    private fun reduceResult(result: R, state: S?): S {
        for (reducer in reducers) {
            if (reducer.couldReduce(result)) {
                return reducer.reduce(state, result)
            }
        }
        throw IllegalArgumentException("Unexpected result type: $result. Provide reducer for this result.")
    }

    fun postIntent(intent: I) {
        Timber.d("postIntent: $intent")
        viewModelScope.launch(Dispatchers.Default) {
            processIntent(intent)
        }
    }

    abstract suspend fun processIntent(intent: I)
}