package com.example.viewmodel.ui.common

sealed class UiEvent{

    object PopBackStack: UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()

}
