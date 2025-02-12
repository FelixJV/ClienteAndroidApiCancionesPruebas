package com.example.composefotosappfjv.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composefotosappfjv.ui.common.BottomBar
import com.example.composefotosappfjv.ui.common.TopBar
import com.example.composefotosappfjv.ui.navigation.Destination.LoginDestination
import com.example.composefotosappfjv.ui.pantallaCanciones.CancionesScreen
import com.example.composefotosappfjv.ui.pantallaDetalleCancion.DetalleCancionScreen
import com.example.composefotosappfjv.ui.pantallaLogin.LoginScreen
import kotlinx.coroutines.launch

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val showSnackbar = { message: String, showUndo: Boolean, undo: () -> Unit ->
        scope.launch {
            if (showUndo) {
                val result = snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = "UNDO",
                    duration = SnackbarDuration.Short
                )
                if (result == SnackbarResult.ActionPerformed) {
                    undo()
                }
            } else {
                snackbarHostState.showSnackbar(message, duration = SnackbarDuration.Short)
            }
        }
    }

    val state by navController.currentBackStackEntryAsState()
    val currentRoute = state?.destination?.route

    var miTopBarState by remember {
        mutableStateOf(MiTopBarState())
    }

    LaunchedEffect(currentRoute) {
        miTopBarState = when (currentRoute) {
            "canciones" -> MiTopBarState(
                title = "Canciones",
                showBackButton = false
            )
            "detalleCancion/{id}" -> MiTopBarState(
                title = "Detalle Canción",
                showBackButton = true,
                onNavigateBack = { navController.popBackStack() }
            )
            else -> MiTopBarState()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            BottomBar(navController = navController)
        },
        topBar = {
            TopBar(miTopBarState = miTopBarState)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginDestination.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(LoginDestination.route) {
                LoginScreen(navigateToCanciones = {
                    navController.navigate(Destination.CancionesDestination.route)
                })
            }
            composable(Destination.CancionesDestination.route) {
                CancionesScreen(navigateToDetalle = { id ->
                    navController.navigate(Destination.DetalleCancionDestination.createRoute(id))
                })
            }
            composable(Destination.DetalleCancionDestination.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
                DetalleCancionScreen(id = id)
            }
        }
    }

}

data class MiTopBarState(
    val title: String = "",
    val showBackButton: Boolean = false,
    val onNavigateBack: () -> Unit = {}
)