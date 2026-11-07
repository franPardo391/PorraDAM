package org.elcampico.porradam.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Estilo «marcador de estadio»: oscuro con amarillo. Sin dynamicColor (Tema 2).
private val Esquema = darkColorScheme(
    primary = Amarillo,
    onPrimary = Marcador,
    surface = Noche,
    onSurface = Color.White,
    onSurfaceVariant = TextoSuave,
    secondaryContainer = Amarillo,
    onSecondaryContainer = Marcador
)

@Composable
fun PorraDamTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Esquema,
        typography = Tipografia,
        content = content
    )
}
