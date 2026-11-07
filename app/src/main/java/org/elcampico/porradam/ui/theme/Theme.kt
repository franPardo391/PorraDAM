package org.elcampico.porradam.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Colores de Material por defecto, cambiando solo el verde.
// Sin dynamicColor: si no, Android 12+ ignora nuestros colores (Tema 2).
private val Claro = lightColorScheme(
    primary = Verde,
    onSurface = Tinta
)

@Composable
fun PorraDamTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Claro,
        typography = Tipografia,
        content = content
    )
}
