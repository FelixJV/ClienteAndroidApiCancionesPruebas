package com.example.moviles1felixjv.ui.pantallaFotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.domain.domain.usecases.fotoUsecase.GetFotos
import com.example.moviles1felixjv.util.Constantes
import com.example.viewmodel.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FotosViewModel @Inject constructor(
    private val getFotosUseCase: GetFotos,
) : ViewModel(){

    private val _uiState = MutableLiveData(FotosState())
    val uiState: LiveData<FotosState> get() = _uiState


    fun handleEvent(event: FotosEvent) {
        when (event) {
            is FotosEvent.getFotos -> getFotos(event.albumId)
        }
    }
    private fun getFotos(albumId: Int) {
        viewModelScope.launch {

            when (val result = getFotosUseCase.invoke()){
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(
                    event = UiEvent.ShowSnackbar(Constantes.ERROR_OBTENER_FOTO)
                )
                is NetworkResult.Loading -> _uiState.value = uiState.value?.copy(isLoading = true)
                is NetworkResult.Success -> {
                    val filteredFotos = result.data.filter { it.idAlbum == albumId }
                    _uiState.value = _uiState.value?.copy(fotos = filteredFotos)
                }
            }

        }

    }

}

