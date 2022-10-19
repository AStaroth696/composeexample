package com.fractaldev.composeexample.lambdatest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LambdaTestViewModel : ViewModel() {

    private val _state = MutableStateFlow(LambdaTestState.initial())
    val state: StateFlow<LambdaTestState> = _state.asStateFlow()

    fun changeInput(newInput: String) {
        _state.update {
            it.copy(input = newInput)
        }
    }

    fun changeLoadingStatus() {
        _state.update {
            it.copy(isLoading = !it.isLoading)
        }
    }

}