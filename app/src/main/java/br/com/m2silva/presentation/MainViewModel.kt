package br.com.m2silva.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.m2silva.network.interceptor.ConnectivityException
import br.com.m2silva.domain.usecase.CustomerUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val usercase: CustomerUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(UIState())
    val uiState: LiveData<UIState> = _uiState

    fun fetchCustomers() {
        viewModelScope.launch {
            val state = UIState()

            _uiState.value = state.setLoading(true)

            usercase.invoke()
                .onSuccess { _uiState.value = state.setCustomers(it) }
                .onFailure {
                    when (it) {
                        is ConnectivityException -> {}
                        else -> {}
                    }
                }
        }
    }
}