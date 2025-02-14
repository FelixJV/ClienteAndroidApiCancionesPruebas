package com.example.composefotosappfjv.ui.pantallaDetalleCancion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.data.remote.di.IoDispatcher
import com.example.composefotosappfjv.data.remote.di.PreferenceRepository
import com.example.composefotosappfjv.domain.modelo.Cancion
import com.example.composefotosappfjv.domain.usecases.cancionUsecases.AddCancion
import com.example.composefotosappfjv.domain.usecases.cancionUsecases.DeleteCancion
import com.example.composefotosappfjv.domain.usecases.cancionUsecases.GetCancion
import com.example.composefotosappfjv.domain.usecases.cancionUsecases.UpdateCancion
import com.example.composefotosappfjv.ui.common.UiEvent
import com.example.composefotosappfjv.util.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleCancionViewModel @Inject constructor(
    private val getCancionUseCase: GetCancion,
    private val updateCancionUseCase: UpdateCancion,
    private val deleteCancionUseCase: DeleteCancion,
    private val addCancionUseCase: AddCancion,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetalleCancionState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: DetalleCancionEvent) {
        when (event) {
            is DetalleCancionEvent.errorMostrado -> eventConsumido()
            is DetalleCancionEvent.ActualizarCancion -> actualizarCancion(event.cancion)
            is DetalleCancionEvent.AddCancion -> addCancion(event.cancion)
            is DetalleCancionEvent.DeleteCancion -> eliminarCancion(event.id)
            is DetalleCancionEvent.GetCancion -> getCancion(event.id)
            is DetalleCancionEvent.UiEventDone -> {
                _uiState.update { it.copy(event = null) }
            }
        }
    }

    private fun getCancion(id: Int) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            getCancionUseCase.invoke(id).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_OBTENER_CANCION)
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                cancion = result.data,
                                event = UiEvent.PopBackStack
                            )
                        }

                    }
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
    private fun addCancion(cancion: Cancion,) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            addCancionUseCase.invoke(cancion).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_ANYADIR_CANCION)
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                cancion = cancion,
                                event = UiEvent.ShowSnackbar(Constantes.CANCION_OBTENIDA)
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
    private fun actualizarCancion(cancion: Cancion) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            updateCancionUseCase.invoke(cancion.id, cancion).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_ACTUALIZAR_CANCION)
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                cancion = cancion,
                                event = UiEvent.ShowSnackbar(Constantes.CANCION_ACTUALIZADA)
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun eliminarCancion(id: Int) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            deleteCancionUseCase.invoke(id).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.CANCION_ELIMINADA)
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_ELIMINAR_CANCION)
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }


    private fun eventConsumido() {
        _uiState.update { it.copy(event = null) }
    }




}
