package com.arcadone.pokestorie.composables

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@Composable
fun PText(
    modifier: Modifier = Modifier,
    text: String = "Text String",
    @StringRes textResources: Int? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    softWrap: Boolean = true,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = ThemeDS.typography.heading,
    color: Color = ThemeDS.colors.black,
    fontWeight: FontWeight = FontWeight.Normal
) {
    val textValue = textResources?.let { stringResource(id = textResources) } ?: text
    Text(
        modifier = modifier,
        text = textValue,
        textAlign = textAlign,
        style = style,
        color = color,
        maxLines = maxLines,
        softWrap = softWrap,
        overflow = overflow,
        fontSize = fontSize,
        fontWeight = fontWeight
    )
}

@Preview
@Composable
fun PTextPreview() {
    PokeStorieAppTheme {
        PText(modifier = Modifier, text = "Text String")
    }
}