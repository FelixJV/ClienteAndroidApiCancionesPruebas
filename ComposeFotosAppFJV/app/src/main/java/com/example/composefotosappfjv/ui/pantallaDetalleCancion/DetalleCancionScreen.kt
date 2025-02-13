package com.example.composefotosappfjv.ui.pantallaDetalleCancion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.composefotosappfjv.domain.modelo.Cancion
import com.example.composefotosappfjv.ui.common.UiEvent
import com.example.composefotosappfjv.ui.pantallaLogin.LoginEvent


@Composable
fun DetalleCancionScreen(
    viewModel: DetalleCancionViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit = {},
    id: Int
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    var nombrePerfil by remember { mutableStateOf("") }
    var nombreArtista by remember { mutableStateOf("") }


    LaunchedEffect(uiState.cancion) {
        uiState.cancion.let {
            nombrePerfil = it.nombre
            nombreArtista = it.artista
        }
    }

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }else if(it is UiEvent.PopBackStack){
                navController.popBackStack()
            }
            viewModel.handleEvent(DetalleCancionEvent.UiEventDone)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nombrePerfil,
            onValueChange = { nombrePerfil = it },
            label = { Text("Título") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        OutlinedTextField(
            value = nombreArtista,
            onValueChange = { nombreArtista = it },
            label = { Text("Artista") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Cambia los valores y dale a añadir para insertar una nueva canción",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                viewModel.handleEvent(DetalleCancionEvent.DeleteCancion(uiState.cancion.id))
            }) {
                Text(text = "Eliminar")
            }
            Button(onClick = {
                val cancion = Cancion(0, nombrePerfil, nombreArtista)
                viewModel.handleEvent(DetalleCancionEvent.AddCancion(cancion))
            }) {
                Text(text = "Añadir")
            }
            Button(onClick = {
                val cancion = Cancion(uiState.cancion.id, nombrePerfil, nombreArtista)
                viewModel.handleEvent(DetalleCancionEvent.ActualizarCancion(cancion, cancion.id))
            }) {
                Text(text = "Update")
            }
        }
    }
}
