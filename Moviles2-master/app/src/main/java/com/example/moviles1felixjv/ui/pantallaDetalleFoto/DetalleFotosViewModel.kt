package com.example.moviles1felixjv.ui.pantallaFotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.domain.domain.modelo.Foto
import com.example.moviles1felixjv.domain.domain.usecases.fotoUsecase.DeleteFoto
import com.example.moviles1felixjv.domain.domain.usecases.fotoUsecase.GetFoto
import com.example.moviles1felixjv.domain.domain.usecases.fotoUsecase.UpdateFoto
import com.example.moviles1felixjv.ui.pantallaDetalleFoto.DetalleFotosEvent
import com.example.moviles1felixjv.ui.pantallaDetalleFoto.DetalleFotosState
import com.example.moviles1felixjv.util.Constantes
import com.example.viewmodel.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleFotosViewModel @Inject constructor(
    private val getFotoUseCase: GetFoto,
    private val updateFotoUseCase: UpdateFoto,
    private val deleteFotouseCase: DeleteFoto
) : ViewModel(){

    private val _uiState = MutableLiveData(DetalleFotosState())
    val uiState: LiveData<DetalleFotosState> get() = _uiState


    fun handleEvent(event: DetalleFotosEvent) {
        when (event) {
            is DetalleFotosEvent.ActualizarFoto -> actualizarFoto(event.foto)
            is DetalleFotosEvent.DeleteFoto -> eliminarFoto(event.id)
            is DetalleFotosEvent.GetFoto -> getFoto(event.idFoto)
            is DetalleFotosEvent.errorMostrado -> TODO()
        }
    }
    private fun getFoto(fotoId: Int) {
        viewModelScope.launch {
            when (val result = getFotoUseCase.invoke(fotoId)) {
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(
                    event = UiEvent.ShowSnackbar(Constantes.ERROR_OBTENER_FOTO)
                )
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(foto = result.data)
                }
                is NetworkResult.Loading -> TODO()
            }
        }
    }
    private fun actualizarFoto(foto: Foto) {
        viewModelScope.launch {
            when (val result = updateFotoUseCase.invoke(foto.idFoto,foto)) {
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(
                    event = UiEvent.ShowSnackbar(Constantes.ERROR_ACTUALIZAR_FOTO)
                )
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(foto = foto)
                }
                is NetworkResult.Loading -> TODO()
            }
        }
    }

    private fun eliminarFoto(fotoId: Int) {
        viewModelScope.launch {
            when (deleteFotouseCase(fotoId)) {

                is NetworkResult.Error -> {
                    _uiState.value = _uiState.value?.copy(
                        event = UiEvent.ShowSnackbar(Constantes.ERROR_ELIMINAR_FOTO)
                    )
                }
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> TODO()
            }
        }
    }
}

