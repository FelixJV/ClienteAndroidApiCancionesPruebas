package com.example.composefotosappfjv.ui.pantallaDetalleCancion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composefotosappfjv.data.remote.di.IoDispatcher
import com.example.composefotosappfjv.domain.usecases.fotoUsecase.DeleteFoto
import com.example.composefotosappfjv.domain.usecases.fotoUsecase.GetFoto
import com.example.composefotosappfjv.domain.usecases.fotoUsecase.UpdateFoto
import com.example.composefotosappfjv.data.remote.NetworkResult
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
class DetalleCancionViewModel @Inject constructor(
    private val getFotoUseCase: GetFoto,
    private val updateFotoUseCase: UpdateFoto,
    private val deleteFotoUseCase: DeleteFoto,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetalleCancionState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: DetalleCancionEvent) {
        when (event) {
            is DetalleCancionEvent.ActualizarFoto -> actualizarFoto(event.foto)
            is DetalleCancionEvent.DeleteFoto -> eliminarFoto(event.id)
            is DetalleCancionEvent.GetFoto -> getFoto(event.idFoto)
            is DetalleCancionEvent.errorMostrado -> eventConsumido()
        }
    }

    private fun getFoto(fotoId: Int) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            getFotoUseCase.invoke(fotoId).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_OBTENER_FOTO)
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                foto = result.data
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

    private fun actualizarFoto(foto: Foto) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            updateFotoUseCase.invoke(foto.idFoto, foto).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_ACTUALIZAR_FOTO)
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                foto = foto,
                                event = UiEvent.ShowSnackbar(Constantes.FOTO_ACTUALIZADA)
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

    private fun eliminarFoto(fotoId: Int) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            deleteFotoUseCase.invoke(fotoId).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.FOTO_ELIMINADA)
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_ELIMINAR_FOTO)
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
