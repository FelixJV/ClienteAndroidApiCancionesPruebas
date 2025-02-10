package com.example.moviles1felixjv.ui.pantallaLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.domain.domain.modelo.User
import com.example.moviles1felixjv.domain.domain.usecases.userUsecase.GetUsers
import com.example.moviles1felixjv.util.Constantes
import com.example.viewmodel.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val getUsersUseCase: GetUsers
) : ViewModel() {

    private val _uiState: MutableLiveData<LoginState> = MutableLiveData(LoginState())
    val uiState: LiveData<LoginState> get() = _uiState

    fun handleEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.validateUser -> validateUser(event.id)
            is LoginEvent.errorMostrado -> errorMostrado()
        }
    }

    private fun validateUser(id: Int) {
        viewModelScope.launch {

            when (val result = getUsersUseCase.invoke()){
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(
                    event = UiEvent.ShowSnackbar(Constantes.ERROR_OBTENER_USER)
                )
                is NetworkResult.Loading -> _uiState.value = uiState.value?.copy(isLoading = true)
                is NetworkResult.Success -> {
                    val users = result.data
                    val user = users.find { it.idUser == id }

                    _uiState.value = if (user != null) {
                        LoginState(user = user, isLoading = false, userEncontrado = true)
                    } else {
                        LoginState(user = null, isLoading = false, mensaje = Constantes.USUARIONOVALIDO)
                    }

                }
            }
        }
    }

    private fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(event = null)
    }

    }
