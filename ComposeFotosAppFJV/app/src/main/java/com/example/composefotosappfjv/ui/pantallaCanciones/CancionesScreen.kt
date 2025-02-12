package com.example.composefotosappfjv.ui.pantallaCanciones

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CancionesScreen(
    viewModel: CancionesViewModel = hiltViewModel(),
    navigateToDetalle : (Int) -> Unit
){
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(CancionesEvent.getCanciones())
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(uiState.canciones) { cancion ->
            Text(
                text = cancion.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { navigateToDetalle(cancion.id) }
            )
        }
    }
}