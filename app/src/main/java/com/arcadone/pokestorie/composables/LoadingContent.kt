package com.arcadone.pokestorie.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ImageGIF()
    }
}

@Preview
@Composable
private fun Preview() {
    PokeStorieAppTheme {
        LoadingContent()
    }
}