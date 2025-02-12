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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DetalleCancionScreen(
    viewModel: DetalleCancionViewModel = hiltViewModel(),
    id: Int
) {
    val uiState by viewModel.uiState.collectAsState()
    var nombrePerfil by rememberSaveable { mutableStateOf("") }
    var nombreArtista by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(Unit) {
        viewModel.handleEvent(DetalleCancionEvent.GetCancion(id))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        uiState.cancion?.let { it ->
            OutlinedTextField(
                value = it.nombre,
                onValueChange = { nombrePerfil = it },
                label = { Text("Título") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            OutlinedTextField(
                value = it.artista,
                onValueChange = { nombreArtista = it },
                label = { Text("Artista") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
        
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Cambia los valores y dale a añadir para insertar una nueva canción"
        )
        Spacer(modifier = Modifier.weight(1f))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {}) {
                Text(text = "Eliminar")
            }
            Button(onClick = {}) {
                Text(text = "Añadir")
            }
            Button(onClick = {}) {
                Text(text = "Update")
            }
        }
    }
}



