package com.arcadone.pokestorie.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcadone.pokestorie.R
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme

@Composable
fun ErrorContent(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageGIF(gifRes = R.drawable.error_gif)
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onRetry) {
            PText(text = "Retry", color = Color.White)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PokeStorieAppTheme {
        ErrorContent {}
    }
}