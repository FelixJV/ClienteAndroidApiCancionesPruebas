package com.example.composefotosappfjv.ui.common

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composefotosappfjv.ui.navigation.Destination


@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomBar(
    navController: NavController,
    ) {
    NavigationBar {
        val state = navController.currentBackStackEntryAsState()

        val currentDestination = state.value?.destination

                NavigationBarItem(
                    icon = {
                       Icon(
                            Icons.Default.Home,
                            tint = MaterialTheme.colorScheme.onSecondary,
                           contentDescription = null)
                           } ,
                    label = {
                        Text(
                            text = "Canciones"
                        )
                            },
                    selected = currentDestination?.hierarchy?.any { it.route == Destination.CancionesDestination::class.qualifiedName } == true,
                    onClick = {
                        navController.navigate(Destination.CancionesDestination){
                            launchSingleTop = true
                        }
                    }
                )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Favorite,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    contentDescription = null)
            } ,
            label = {
                Text(
                    text = "Detalle"
                )
            },
            selected = currentDestination?.hierarchy?.any { it.route == Destination.DetalleCancionDestination::class.qualifiedName } == true,
            onClick = {
                navController.navigate(Destination.DetalleCancionDestination){
                    launchSingleTop = true
                }
            }
        )
            }

    }
