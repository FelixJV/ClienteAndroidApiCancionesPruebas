package com.example.composefotosappfjv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.composefotosappfjv.ui.navigation.Navigation
import com.example.composefotosappfjv.ui.theme.ComposeFotosAppFJVTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFotosAppFJVTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    Navigation()
}