package com.zygne.viewstate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState

    fun performAction(action: Action) {
        when (action) {
            is Action.DecrementBy -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _viewState.value = _viewState.value.copy(loadingState = LoadingState.Loading)
                    delay(action.amount * TIME_TO_WAIT_SCALAR)

                    _viewState.value =
                        _viewState.value.copy(
                            counter = _viewState.value.counter - action.amount,
                            loadingState = LoadingState.None
                        )
                }

            }

            is Action.IncrementBy -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _viewState.value = _viewState.value.copy(loadingState = LoadingState.Loading)
                    delay(action.amount * TIME_TO_WAIT_SCALAR)

                    _viewState.value =
                        _viewState.value.copy(
                            counter = _viewState.value.counter + action.amount,
                            loadingState = LoadingState.None
                        )
                }

            }

        }

    }

    sealed class Action {
        data class IncrementBy(val amount: Int) : Action()
        data class DecrementBy(val amount: Int) : Action()
    }

    sealed class LoadingState {
        data object None : LoadingState()
        data object Loading : LoadingState()
    }

    data class ViewState(
        val counter: Int = 0,
        val loadingState: LoadingState = LoadingState.None
    )

    companion object {
        private const val TIME_TO_WAIT_SCALAR = 250L
    }
}