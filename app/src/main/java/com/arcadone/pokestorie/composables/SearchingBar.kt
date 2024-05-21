package com.arcadone.pokestorie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchingBar(
    modifier: Modifier = Modifier,
    onClickSearch: (String) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    var expanded = false
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        Modifier
            .then(modifier)
            .fillMaxWidth()
            .background(ThemeDS.colors.primary)
            .padding(16.dp)
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = -1f },
            colors = SearchBarDefaults.colors(
                containerColor = ThemeDS.colors.lightBlue,
                dividerColor = ThemeDS.colors.orange
            ),
            inputField = {
                SearchBarDefaults.InputField(
                    query = text,
                    colors = SearchBarDefaults.inputFieldColors()
                        .copy(focusedTextColor = ThemeDS.colors.orange),
                    onQueryChange = { text = it },
                    onSearch = {
                        onClickSearch(it)
                        keyboardController?.hide()
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = {
                        if (text.isEmpty()) {
                            PText(
                                color = ThemeDS.colors.orange,
                                style = ThemeDS.typography.label.copy(fontWeight = FontWeight.Normal),
                                text = "Search by name or Id"
                            )
                        }
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = ThemeDS.colors.orange
                        )
                    },
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun TextPreview() {
    PokeStorieAppTheme {
        SearchingBar(onClickSearch = {})
    }
}
