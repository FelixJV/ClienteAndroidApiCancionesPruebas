package com.example.composefotosappfjv.ui.pantallaLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composefotosappfjv.data.remote.di.IoDispatcher
import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.domain.modelo.User
import com.example.composefotosappfjv.domain.usecases.userUsecase.GetUser
import com.example.composefotosappfjv.domain.usecases.userUsecase.RegisterUser
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
class LoginViewModel @Inject constructor(
    private val getUserUseCase: GetUser,
    private val registerUser: RegisterUser,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.registerUser -> createUser(event.user)
            is LoginEvent.loginUser -> login(event.name,event.pass)
            LoginEvent.UiEventDone -> {
                _uiState.update { it.copy(event = null) }
            }
        }
    }

    private fun login(name:String, pass:String) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            getUserUseCase.invoke(name, pass).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.ERROR_OBTENER_USER)
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is NetworkResult.Success -> {
                        val token = result.data

                            _uiState.update {
                                it.copy(isLoading = false, userEncontrado = true, event = UiEvent.Navigate("asasd"))
                            }


                    }
                }
            }
        }
    }

    private fun createUser(user: User) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true) }

            registerUser.invoke(user).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.USUARIOCREADO)
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                event = UiEvent.ShowSnackbar(Constantes.USUARIONOCREADO)
                            )
                        }
                    }
                }
            }
        }
    }


}
