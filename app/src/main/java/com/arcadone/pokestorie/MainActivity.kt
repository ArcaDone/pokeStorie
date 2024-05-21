package com.arcadone.pokestorie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arcadone.pokestorie.presentation.HomeScreen
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeStorieAppTheme {
                HomeScreen()
            }
        }
    }
}
