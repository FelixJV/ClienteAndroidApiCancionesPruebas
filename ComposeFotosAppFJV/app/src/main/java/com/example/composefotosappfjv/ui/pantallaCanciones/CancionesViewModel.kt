package com.example.composefotosappfjv.ui.pantallaCanciones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composefotosappfjv.data.remote.di.IoDispatcher
import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.domain.usecases.cancionUsecases.GetCanciones
import com.example.composefotosappfjv.util.Constantes
import com.example.composefotosappfjv.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancionesViewModel @Inject constructor(
    private val getCancionesUseCase: GetCanciones,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(CancionesState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: CancionesEvent) {
        when (event) {
            is CancionesEvent.getCanciones -> getCanciones()
            is CancionesEvent.UiEventDone -> eventDone()
        }
    }

    private fun getCanciones() {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            getCancionesUseCase.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_OBTENER_CANCIONES)
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is NetworkResult.Success -> {
                        val canciones = result.data
                        _uiState.update {
                            it.copy(
                                canciones = canciones,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun eventDone() {
        _uiState.update { it.copy(event = null) }
    }
}
