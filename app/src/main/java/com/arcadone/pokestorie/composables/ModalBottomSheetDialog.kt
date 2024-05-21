package com.arcadone.pokestorie.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetDialog(
    modifier: Modifier = Modifier,
    pokemonInfo: PokemonInfo,
    bottomSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
    onDismissRequest: (Boolean) -> Unit
) {
    ModalBottomSheet(
        modifier = Modifier.then(modifier),
        containerColor = ThemeDS.colors.white,
        onDismissRequest = {
            onDismissRequest(false)
        },
        sheetState = bottomSheetState,
        content = {
            PokemonDetailScreen(pokemonInfo)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetPreview() {
    val samplePokemonInfo = PokemonInfo(
        name = "Pikachu",
        imageUrl = "",
        imageSVG = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
        types = listOf("Electric"),
        description = "Pikachu that can generate powerful electricity have cheek sacs that are extra soft and super stretchy.",
        abilities = listOf("Static", "Lightning Rod"),
        moves = listOf("Quick Attack", "Thunderbolt"),
        baseStats = listOf(),
        evolutionChain = listOf(
            PokemonInfo.EvolutionInfo("Pichu", null, "Friendship"),
            PokemonInfo.EvolutionInfo("Pikachu", null, "Thunder Stone"),
            PokemonInfo.EvolutionInfo("Raichu", null, "Thunder Stone")
        )
    )

    PokeStorieAppTheme {
        ModalBottomSheetDialog(pokemonInfo = samplePokemonInfo, onDismissRequest = {})
    }
}